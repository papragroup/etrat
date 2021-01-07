package com.etrat.web.rest;

import com.etrat.domain.User;
import com.etrat.domain.UserType;
import com.etrat.security.jwt.JWTFilter;
import com.etrat.security.jwt.TokenProvider;
import com.etrat.service.UserService;
import com.etrat.service.dto.OtpDto;
import com.etrat.service.dto.UserDTO;
import com.etrat.web.rest.vm.LoginVM;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jhipster.security.RandomUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController extends SmsData {
    Map<String, OtpDto> otpMap = new HashMap<>();
    private final TokenProvider tokenProvider;

    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserJWTController(
        TokenProvider tokenProvider,
        UserService userService,
        AuthenticationManagerBuilder authenticationManagerBuilder
    ) {
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @org.springframework.web.bind.annotation.RequestBody LoginVM loginVM) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            loginVM.getUsername(),
            loginVM.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/send-otp")
    public ResponseEntity<Void> sendOtp(@RequestParam(name = "phone-number") String phoneNumber)
        throws IOException, SAXException, ParserConfigurationException, TransformerException {
        if (otpMap.get(phoneNumber) == null || otpMap.get(phoneNumber).getExpireTime() < System.currentTimeMillis()) {
            String format = new DecimalFormat("0000").format(new Random().nextInt(9999));
            long currentTimeMillis = System.currentTimeMillis() + (120 * 1000);
            OtpDto otpDto = new OtpDto();
            otpDto.setOtp(format);
            otpDto.setExpireTime(currentTimeMillis);
            otpMap.put(phoneNumber, otpDto);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(body)));

            //        Document doc = builder.parse(body);
            NodeList flowList = doc.getElementsByTagName("tem:cSmsnumber");
            flowList.item(0).setTextContent(phoneNumber);
            NodeList smsBody = doc.getElementsByTagName("tem:cBody");
            smsBody.item(0).setTextContent("کد ورود شما به عترت: " + format);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Write the XML into a buffernode
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            StreamResult finalResult = new StreamResult(output);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, finalResult);
            OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .writeTimeout(1, TimeUnit.HOURS)
                .connectTimeout(1, TimeUnit.HOURS)
                .readTimeout(1, TimeUnit.HOURS)
                .build();
            MediaType mediaType = MediaType.parse("text/xml; charset=utf-8");
            RequestBody body = RequestBody.create(mediaType, new String(output.toByteArray()));
            Request request = new Request.Builder()
                .url("http://mehrafraz.com/webservice/service.asmx")
                .method("POST", body)
                .addHeader("SOAPAction", "http://tempuri.org/SendSms")
                .addHeader("Content-Type", "text/xml; charset=utf-8")
                .build();
            Response response = client.newCall(request).execute();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<JWTToken> verify(@RequestParam(name = "phone-number") String phoneNumber, @RequestParam(name = "otp") String otp)
        throws IllegalAccessException {
        if (
            otpMap.get(phoneNumber).getOtp() != null &&
            otpMap.get(phoneNumber).getOtp().equals(otp) &&
            otpMap.get(phoneNumber).getExpireTime() > System.currentTimeMillis()
        ) {
            String password = RandomUtil.generatePassword();
            UserDTO userResult = userService
                .getUserWithAuthoritiesByLogin(phoneNumber)
                .filter(Objects::nonNull)
                .map(
                    u -> {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setLogin(phoneNumber);
                        userDTO.setId(u.getId());
                        userDTO.setActivated(true);
                        userDTO.getAuthorities().add("ROLE_USER");
                        userService.updateUser(userDTO, password, UserType.MEHMAN);
                        return userDTO;
                    }
                )
                .orElseGet(
                    () -> {
                        UserDTO userDTO = new UserDTO();
                        userDTO.setLogin(phoneNumber);
                        userDTO.setActivated(false);
                        userDTO.getAuthorities().add("ROLE_USER");
                        userService.registerUser(userDTO, password, UserType.MEHMAN);
                        return userDTO;
                    }
                );

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phoneNumber, password);

            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication, false);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
            return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
        } else {
            throw new IllegalAccessException();
        }
    }

    public void removeExpire() {
        otpMap.forEach(
            (k, v) -> {
                if (v.getExpireTime() < System.currentTimeMillis()) {
                    otpMap.remove(k);
                }
            }
        );
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}

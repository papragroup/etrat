package com.etrat.util;

import com.etrat.domain.User;
import com.etrat.repository.UserRepository;
import com.etrat.service.UserService;
import com.etrat.service.dto.Hami;
import com.etrat.service.dto.HamiDTO;
import com.etrat.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EtratWarpperUtil {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    public void  getHami(){
        HamiDTO hamiDTO = restTemplate.getForEntity("http://localhost:8081/api/v1/hami?hami-id=1", HamiDTO.class).getBody();
        for (Hami hami:hamiDTO.getHamis()){
            UserDTO user=new UserDTO();
            user.setLogin(hami.getShenase().toString());
            userService.registerUser(user,hami.getShenase().toString());
        }
    }
}

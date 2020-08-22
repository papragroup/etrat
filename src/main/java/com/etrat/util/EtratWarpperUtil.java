package com.etrat.util;

import com.etrat.domain.HamiLastId;
import com.etrat.repository.HamiLastIdRepository;
import com.etrat.service.UserService;
import com.etrat.service.dto.Hami;
import com.etrat.service.dto.HamiDTO;
import com.etrat.service.dto.UserDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EtratWarpperUtil {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private HamiLastIdRepository hamiRepository;

    @Value("${etrat-warrper-url}")
    private String etarturl;

    public void getHami() {
        Long lastId = hamiRepository
            .findById(1l)
            .filter(h -> h != null)
            .map(h -> h.getLastHamiId())
            .orElseGet(
                () -> {
                    HamiLastId hamiLastId = new HamiLastId();
                    hamiLastId.setLastHamiId(1l);
                    hamiRepository.save(hamiLastId);
                    return hamiLastId.getLastHamiId();
                }
            );
        HamiDTO hamiDTO = restTemplate.getForEntity(etarturl + "/api/v1/hami?hami-id=" + lastId, HamiDTO.class).getBody();
        for (Hami hami : hamiDTO.getHamis()) {
            try {
                UserDTO user = new UserDTO();
                user.setLogin(hami.getShenase().toString());
                user.setFirstName(hami.getFirstName());
                user.setLastName(hami.getLastName());
                userService.registerUser(user, hami.getShenase().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!hamiDTO.getHamis().isEmpty()) {
            Optional<HamiLastId> result = hamiRepository.findById(1l);
            HamiLastId hamiLastId = result.get();
            hamiLastId.setLastHamiId(Long.valueOf(hamiDTO.getHamis().get(hamiDTO.getHamis().size() - 1).getHamiId()));
            hamiRepository.save(hamiLastId);
        }
    }

    public Long saveInEtratWrapper(VariziHami variziHami) {
        HttpEntity httpEntity = new HttpEntity(variziHami);
        return restTemplate.postForEntity(etarturl + "/api/v1/varizi", httpEntity, VariziHami.class).getBody().getId();
    }
}

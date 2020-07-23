package com.etrat.util;

import com.etrat.domain.HamiLastId;
import com.etrat.repository.HamiLastIdRepository;
import com.etrat.service.UserService;
import com.etrat.service.dto.Hami;
import com.etrat.service.dto.HamiDTO;
import com.etrat.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EtratWarpperUtil {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private HamiLastIdRepository hamiRepository;

    public void  getHami(){
        Long lastId = hamiRepository.findById(1l).filter(h -> h != null)
            .map((h) -> h.getLastHamiId())
            .orElseGet(() -> {
                HamiLastId hamiLastId = new HamiLastId();
                hamiLastId.setLastHamiId(1l);
                hamiRepository.save(hamiLastId);
                return hamiLastId.getLastHamiId();
            });
        HamiDTO hamiDTO = restTemplate.getForEntity("http://etrat-warraper/api/v1/hami?hami-id="+lastId, HamiDTO.class).getBody();
        for (Hami hami:hamiDTO.getHamis()){
            try {
                UserDTO user=new UserDTO();
                user.setLogin(hami.getShenase().toString());
                userService.registerUser(user,hami.getShenase().toString());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if (!hamiDTO.getHamis().isEmpty()) {
            Optional<HamiLastId> result= hamiRepository.findById(1l);
            HamiLastId hamiLastId = result.get();
            hamiLastId.setLastHamiId(Long.valueOf(hamiDTO.getHamis().get(hamiDTO.getHamis().size()-1).getHamiId()));
            hamiRepository.save(hamiLastId);
        }
    }
}

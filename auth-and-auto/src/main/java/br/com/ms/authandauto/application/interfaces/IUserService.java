package br.com.ms.authandauto.application.interfaces;


import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserMicroserviceResponse> getUsersAndPermissions();

    void saveUser(UserDTO userDTO);

    UserDTO findById(Long id);

}

package br.com.ms.authandauto.application.interfaces;


import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.domain.model.Enum.Role;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.Requests.UserMicroserviceRequest;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserMicroserviceResponse> getUsersAndPermissions();
    void updateUserRoleInMicroservice(Long userId, Long microserviceId, Role newRole);
    void bindUserToMicroservice(Long userId, Long microserviceId, UserMicroserviceRequest request);

    void saveUser(UserDTO userDTO);


}

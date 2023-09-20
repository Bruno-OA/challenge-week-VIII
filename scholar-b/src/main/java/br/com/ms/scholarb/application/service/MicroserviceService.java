package br.com.ms.scholarb.application.service;

import br.com.ms.scholarb.domain.model.microsservice.Microservice;
import br.com.ms.scholarb.domain.model.user.UserResponse;
import br.com.ms.scholarb.domain.model.microsservice.MicroserviceResponse;
import br.com.ms.scholarb.infra.FeingClient.UserMicroserviceRoleFeign;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MicroserviceService {

    private final UserMicroserviceRoleFeign _userMicroserviceRoleFeign;
    public MicroserviceService(UserMicroserviceRoleFeign userMicroserviceRoleFeign) {
        _userMicroserviceRoleFeign = userMicroserviceRoleFeign;
    }

    public MicroserviceResponse getMicroserviceByIdAndUsersPermissions(Long microserviceId) {
        Microservice microservice = _userMicroserviceRoleFeign.getMicroserviceByMicroserviceId(microserviceId);
        MicroserviceResponse response = new MicroserviceResponse();
        response.setId(microserviceId);
        response.setName(microservice.getName());
        List<UserResponse> users = _userMicroserviceRoleFeign.getUserByMicroserviceId(microserviceId);
        response.setUsers(users);
        return response;
    }
}

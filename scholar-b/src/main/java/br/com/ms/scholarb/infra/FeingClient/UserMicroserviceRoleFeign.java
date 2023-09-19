package br.com.ms.scholarb.infra.FeingClient;

import br.com.ms.scholarb.domain.model.microsservice.Microservice;
import br.com.ms.scholarb.domain.model.user.UserResponse;
import br.com.ms.scholarb.domain.model.userMicroserviceRole.UserMicroserviceRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ms-auth-and-auth", url = "http://localhost:8080/ms-auth-and-auto")
public interface UserMicroserviceRoleFeign {

    @GetMapping(value = "/api/userMicroserviceRole/user/{id-user}/microservice/{id-microservice}")
    UserMicroserviceRole findById(@PathVariable("id-user") Long userId,
                                  @PathVariable("id-microservice") Long microserviceId);

    @GetMapping(value = "/api/userMicroserviceRole/{microserviceId}")
    Microservice getMicroserviceByMicroserviceId(@PathVariable Long microserviceId);

    @GetMapping(value = "api/userMicroserviceRole/{microserviceId}/users")
    List<UserResponse> getUserByMicroserviceId(@PathVariable Long microserviceId);
}


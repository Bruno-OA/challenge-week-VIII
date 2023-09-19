package br.com.ms.authandauto.application.interfaces;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.dtos.UserMicroserviceRoleDTO;

import java.util.List;

public interface IUserMicroserviceRoleService {
    UserMicroserviceRoleDTO findByUserAndMicroservice(Long userId, Long microserviceId);
    MicroserviceDTO findMicroserviceById(Long id);

}

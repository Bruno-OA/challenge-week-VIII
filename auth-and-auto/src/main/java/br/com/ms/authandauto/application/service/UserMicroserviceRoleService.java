package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.dtos.UserMicroserviceRoleDTO;
import br.com.ms.authandauto.application.interfaces.IUserMicroserviceRoleService;
import br.com.ms.authandauto.domain.interfaces.IUserMicroserviceRoleRepository;
import br.com.ms.authandauto.domain.model.user.Reponse.UserRoleResponse;
import br.com.ms.authandauto.domain.model.userMicroserviceRole.UserMicroserviceRole;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserMicroserviceRoleService implements IUserMicroserviceRoleService {

    private final IUserMicroserviceRoleRepository _userMicroserviceRoleRepository;
    private final ModelMapper _modelMapper;

    @Autowired
    public UserMicroserviceRoleService(IUserMicroserviceRoleRepository userMicroserviceRoleRepository, ModelMapper modelMapper) {
        _userMicroserviceRoleRepository = userMicroserviceRoleRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public UserMicroserviceRoleDTO findByUserAndMicroservice(Long userId, Long microserviceId) {
        UserMicroserviceRole userMicroserviceRole = _userMicroserviceRoleRepository
                .findByUserIdAndMicroserviceId(userId, microserviceId);
        return _modelMapper.map(userMicroserviceRole, UserMicroserviceRoleDTO.class);
    }

    @Override
    public MicroserviceDTO findMicroserviceById(Long id) {
        UserMicroserviceRole userMicroserviceRole = _userMicroserviceRoleRepository.
                findFirstByMicroserviceId(id);
        String name = userMicroserviceRole.getMicroservice().getName();
        Long microserviceId = userMicroserviceRole.getMicroservice().getId();
        MicroserviceDTO microserviceDTO = new MicroserviceDTO();
        microserviceDTO.setId(microserviceId);
        microserviceDTO.setName(name);
        return microserviceDTO;
    }

    @Override
    public List<UserRoleResponse> findUserByMicroserviceId(Long microserviceId) {
        List<UserMicroserviceRole> userMicroserviceRoles = _userMicroserviceRoleRepository.findUserByMicroserviceId(microserviceId);

        return userMicroserviceRoles.stream()
                .map(userMicroserviceRole -> {
                    UserRoleResponse response = new UserRoleResponse();
                    response.setName(userMicroserviceRole.getUser().getName());
                    response.setRole(userMicroserviceRole.getRole().toString());
                    return response;
                })
                .collect(toList());
    }

}

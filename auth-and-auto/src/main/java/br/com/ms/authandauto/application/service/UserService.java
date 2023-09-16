package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.dtos.UserMicroserviceRoleDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.application.interfaces.IUserService;
import br.com.ms.authandauto.domain.interfaces.IUserRepository;
import br.com.ms.authandauto.domain.model.Enum.Role;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.Requests.UserMicroserviceRequest;
import  br.com.ms.authandauto.domain.model.microsservice.Response.MicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.User;
import br.com.ms.authandauto.domain.model.userMicroserviceRole.UserMicroserviceRole;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository _userRepository;
    private final IMicroserviceService _microserviceService;
    private final ModelMapper _modelMapper;

    @Autowired
    public UserService(IUserRepository userRepository,
                       IMicroserviceService microserviceService,
                        ModelMapper modelMapper) {
        _userRepository = userRepository;
        _microserviceService = microserviceService;
        _modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = _modelMapper.map(userDTO, User.class);
        return _modelMapper.map(_userRepository.save(user), UserDTO.class);
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = _modelMapper.map(userDTO, User.class);
        _modelMapper.map(_userRepository.save(user), UserDTO.class);
    }

    @Override
    public List<UserMicroserviceResponse> getUsersAndPermissions() {
        List<User> users = _userRepository.findAll();
        List<UserMicroserviceResponse> responses = new ArrayList<>();

        for (User user : users) {
            UserMicroserviceResponse response = new UserMicroserviceResponse();
            response.setName(user.getName());
            response.setEmail(user.getEmail());

            List<MicroserviceResponse> microservices = new  ArrayList<>();
            for (UserMicroserviceRole userMicroservice : user.getUserMicroservices()) {
                MicroserviceResponse microserviceResponse = new MicroserviceResponse();
                microserviceResponse.setName(userMicroservice.getMicroservice().getName());
                microserviceResponse.setRoleUser(userMicroservice.getRole().toString());
                microservices.add(microserviceResponse);
            }
            response.setMicroservices(microservices);
            responses.add(response);
        }
        return responses;
    }

    @Override
    public void updateUserRoleInMicroservice(Long userId, Long microserviceId, Role newRole) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Microservice microservice = _modelMapper.map(_microserviceService
                .findById(microserviceId), Microservice.class);

        UserMicroserviceRole existingUserMicroservice = user.getUserMicroservices().stream()
                .filter(userMicroservice -> userMicroservice.getMicroservice().equals(microservice))
                .findFirst()
                .orElse(null);

        if (existingUserMicroservice != null) {
            existingUserMicroservice.setRole(newRole);
        } else {
            UserMicroserviceRole userMicroserviceRole = new UserMicroserviceRole();
            userMicroserviceRole.setUser(user);
            userMicroserviceRole.setMicroservice(microservice);
            userMicroserviceRole.setRole(newRole);

            user.getUserMicroservices().add(userMicroserviceRole);
        }

        _userRepository.save(user);
    }
    @Override
    public void bindUserToMicroservice(Long userId, Long microserviceId, UserMicroserviceRequest request) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Microservice microservice = _modelMapper.map(_microserviceService
                .findById(microserviceId), Microservice.class);
        Role role = Role.USER;
        boolean alreadyHasMicroservice = user.getUserMicroservices().stream()
                .anyMatch(userMicroservice ->
                        userMicroservice.getMicroservice().equals(microservice) &&
                                userMicroservice.getUser().getEmail().equals(request.getEmailUser()));

        if (!alreadyHasMicroservice) {
            UserMicroserviceRoleDTO userMicroserviceRoleDTO = new UserMicroserviceRoleDTO();
            userMicroserviceRoleDTO.setUser(user);
            userMicroserviceRoleDTO.setMicroservice(microservice);
            userMicroserviceRoleDTO.setRole(role);

            user.getUserMicroservices().add(_modelMapper.map(userMicroserviceRoleDTO, UserMicroserviceRole.class) );
            _userRepository.save(user);
        }
    }
}

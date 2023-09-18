package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.MicroserviceDTO;
import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.dtos.UserMicroserviceRoleDTO;
import br.com.ms.authandauto.application.interfaces.IMicroserviceService;
import br.com.ms.authandauto.application.interfaces.IUserService;
import br.com.ms.authandauto.infra.constants.ErrorConstants;
import br.com.ms.authandauto.infra.exceptions.*;
import br.com.ms.authandauto.domain.enums.ErrorCodes;
import br.com.ms.authandauto.domain.interfaces.IUserRepository;
import br.com.ms.authandauto.domain.enums.Role;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.model.user.Requests.UserMicroserviceRequest;
import br.com.ms.authandauto.domain.model.microsservice.Response.MicroserviceResponse;
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
        if (_userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new DuplicatedEmailException(
                    new ExceptionResponse(ErrorCodes.BAD_REQUEST, ErrorConstants.USER_ALREADY_EXISTS));
        }
        return _modelMapper.map(_userRepository.save(user), UserDTO.class);
    }

    @Override
    public List<UserMicroserviceResponse> getUsersAndPermissions() {
        List<User> users = _userRepository.findAll();
        List<UserMicroserviceResponse> responses = new ArrayList<>();

        for (User user : users) {
            UserMicroserviceResponse response = new UserMicroserviceResponse();
            response.setName(user.getName());
            response.setEmail(user.getEmail());

            List<MicroserviceResponse> microservices = new ArrayList<>();
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
                .orElseThrow(() -> new UserNotFoundException(new ExceptionResponse(ErrorCodes.USER_NOT_FOUND,
                        ErrorConstants.USER_NOT_FOUND)));

        Microservice microservice = _modelMapper.map(_microserviceService
                .findById(microserviceId), Microservice.class);

        UserMicroserviceRole existingUser = user.getUserMicroservices().stream()
                .filter(userMicroservice -> userMicroservice.getUser().equals(user))
                .findFirst()
                .orElse(null);

        UserMicroserviceRole existingUserMicroservice = user.getUserMicroservices().stream()
                .filter(userMicroservice -> userMicroservice.getMicroservice().equals(microservice))
                .findFirst()
                .orElse(null);

        if (existingUserMicroservice != null && existingUser != null) {
            existingUserMicroservice.setRole(newRole);
        }
        if (existingUser == null) {
            throw new UserNotFoundException(
                    new ExceptionResponse(ErrorCodes.USER_NOT_FOUND,
                            ErrorConstants.USER_NOT_FOUND)
            );
        }
        if (existingUserMicroservice == null) {
            throw new MicroserviceNotFoundException(
                    new ExceptionResponse(ErrorCodes.MICROSERVICE_NOT_FOUND,
                            ErrorConstants.MICROSERVICE_NOT_FOUND)
            );
        }
        _userRepository.save(user);
    }

    @Override
    public void bindUserToMicroservice(Long userId, Long microserviceId, UserMicroserviceRequest request) {
        User user = _userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(new ExceptionResponse(ErrorCodes.USER_NOT_FOUND,
                        ErrorConstants.USER_NOT_FOUND)));

        MicroserviceDTO microserviceDTO = _microserviceService.findById(microserviceId);
        Microservice microservice = _modelMapper.map(microserviceDTO, Microservice.class);
        if (microservice.getId() == null) {
            throw new MicroserviceNotFoundException(new ExceptionResponse(ErrorCodes.MICROSERVICE_NOT_FOUND,
                    ErrorConstants.MICROSERVICE_NOT_FOUND));
        }
        if (!user.getEmail().equals(request.getEmailUser())) {
            throw new InvalidEmailException(new ExceptionResponse(ErrorCodes.INVALID_EMAIL,
                    ErrorConstants.USER_NOT_FOUND));
        }

        if (!microservice.getName().equals(request.getNameMicroservice())) {
            throw new InvalidMicroserviceNameException(new ExceptionResponse(ErrorCodes.INVALID_MICROSERVICE_NAME,
                    ErrorConstants.MICROSERVICE_NOT_FOUND));
        }

        boolean alreadyHasMicroservice = user.getUserMicroservices().stream()
                .anyMatch(userMicroservice ->
                        userMicroservice.getMicroservice().equals(microservice));

        if (!alreadyHasMicroservice) {
            Role role = Role.USER;
            UserMicroserviceRoleDTO userMicroserviceRoleDTO = new UserMicroserviceRoleDTO();
            userMicroserviceRoleDTO.setUser(user);
            userMicroserviceRoleDTO.setMicroservice(microservice);
            userMicroserviceRoleDTO.setRole(role);
            user.getUserMicroservices().add(_modelMapper.map(userMicroserviceRoleDTO, UserMicroserviceRole.class));
            _userRepository.save(user);
        }else {
            throw new MicroserviceAlreadyExistsInUserExcept(new ExceptionResponse(ErrorCodes.MICROSERVICE_ALREADY_EXISTS_IN_USER,
                    ErrorConstants.MICROSERVICE_ALREADY_EXISTS));
        }


    }
    @Override
    public UserDTO findById(Long id) {
        User user   = _userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                new ExceptionResponse(ErrorCodes.USER_NOT_FOUND, ErrorConstants.USER_NOT_FOUND)));

        return _modelMapper.map(user, UserDTO.class);

    }
}

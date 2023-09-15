package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.interfaces.IUserService;
import br.com.ms.authandauto.domain.model.microsservice.Microservice;
import br.com.ms.authandauto.domain.model.user.Reponse.UserMicroserviceResponse;
import br.com.ms.authandauto.domain.interfaces.IUserRepository;
import br.com.ms.authandauto.domain.model.user.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserRepository _userRepository;
    private final ModelMapper _modelMapper;
    @Autowired
    public UserService(IUserRepository userRepository, ModelMapper modelMapper) {
        _userRepository = userRepository;
        _modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = _modelMapper.map(userDTO, User.class);
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

            List<Microservice> microservices = user.getMicroservices();
            response.setMicroservices(microservices);

            responses.add(response);
        }
        return responses;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        User user = _modelMapper.map(userDTO, User.class);
        _modelMapper.map(_userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }
}

package br.com.ms.authandauto.application.service;

import br.com.ms.authandauto.application.dtos.UserDTO;
import br.com.ms.authandauto.application.interfaces.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {


    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        return null;
    }

    @Override
    public void saveUser(UserDTO userDTO) {

    }

    @Override
    public UserDTO findById(Long id) {
        return null;
    }
}

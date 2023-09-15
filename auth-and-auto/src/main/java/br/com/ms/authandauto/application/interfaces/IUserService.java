package br.com.ms.authandauto.application.interfaces;


import br.com.ms.authandauto.application.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> findAll();

    void saveUser(UserDTO userDTO);

    UserDTO findById(Long id);

}

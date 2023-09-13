package br.com.ms.authandauto.controller;

import br.com.ms.authandauto.dto.UserRequestDTO;
import br.com.ms.authandauto.model.User;
import br.com.ms.authandauto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    public void createUser(@RequestBody UserRequestDTO data) {
        User userData = new User(data);
        repository.save(userData);
        return;
    }
}

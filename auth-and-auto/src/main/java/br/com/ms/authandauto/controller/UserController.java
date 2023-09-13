package br.com.ms.authandauto.controller;

import br.com.ms.authandauto.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "/create-user";
    }
}

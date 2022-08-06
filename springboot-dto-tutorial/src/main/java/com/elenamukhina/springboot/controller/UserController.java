package com.elenamukhina.springboot.controller;

import com.elenamukhina.springboot.dto.UserLocationDTO;
import com.elenamukhina.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // @GetMapping означает, что по указанному урлу будет выполнен/обработан гет-запрос
    @GetMapping("/users-location")
    public List<UserLocationDTO> getAllUsersLocation() {
        return userService.getAllUsersLocation();
    }
}

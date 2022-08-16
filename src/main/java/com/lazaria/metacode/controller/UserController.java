package com.lazaria.metacode.controller;


import com.lazaria.metacode.dto.*;
import com.lazaria.metacode.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    private List<User> findAllTopics() {
        return userService.getUsers();
    }

    @PostMapping("/save")
    private User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/role/save")
    private Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    @PostMapping("/addRoleToUser")
    private void addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.userLogin, form.roleName);
    }

    @Data
    static
    class RoleToUserForm {
        private String userLogin;
        private String roleName;
    }

}



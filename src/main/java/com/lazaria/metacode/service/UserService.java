package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Role;
import com.lazaria.metacode.dto.User;
import com.lazaria.metacode.repository.RoleRepository;
import com.lazaria.metacode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void addRoleToUser(String userLogin, String roleName) {
        User user = userRepository.findByUserLogin(userLogin);
        Role role = roleRepository.findByRoleName(roleName);
        user.getRoles().add(role);
    }

    public User getUser(String userLogin) {
        return userRepository.findByUserLogin(userLogin);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}

package com.lazaria.metacode.service;

import com.lazaria.metacode.dto.Role;
import com.lazaria.metacode.dto.User;
import com.lazaria.metacode.repository.RoleRepository;
import com.lazaria.metacode.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userRepository.findByUserLogin(userLogin);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserLogin(),user.getUserPassword(), simpleGrantedAuthorities);
    }
}

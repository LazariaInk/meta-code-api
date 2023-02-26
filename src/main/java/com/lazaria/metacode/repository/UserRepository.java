package com.lazaria.metacode.repository;

import java.util.Optional;

import com.lazaria.metacode.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

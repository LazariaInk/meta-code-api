package com.lazaria.metacode.repository;

import com.lazaria.metacode.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}

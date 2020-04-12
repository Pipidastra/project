package com.onlytours.repository;

import com.onlytours.entity.Role;
import com.onlytours.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(Roles role);
}

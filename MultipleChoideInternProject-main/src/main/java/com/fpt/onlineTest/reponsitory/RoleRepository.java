package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);

}

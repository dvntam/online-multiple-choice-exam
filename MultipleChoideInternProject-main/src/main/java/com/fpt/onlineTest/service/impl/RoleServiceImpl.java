package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Role;
import com.fpt.onlineTest.reponsitory.RoleRepository;
import com.fpt.onlineTest.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }



    @Override
    public <S extends Role> S save(S entity) {
        return roleRepository.save(entity);
    }
}

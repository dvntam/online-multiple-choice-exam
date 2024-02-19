package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Role;

public interface RoleService {
    Role findByRoleName(String roleName);

    <S extends Role> S save(S entity);
}

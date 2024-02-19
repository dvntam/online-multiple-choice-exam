package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.Admin;

public interface AdminService {
    boolean isUsernameTaken(String username);

    Admin findByUsername(String username);

    <S extends Admin> S save(S entity);

}

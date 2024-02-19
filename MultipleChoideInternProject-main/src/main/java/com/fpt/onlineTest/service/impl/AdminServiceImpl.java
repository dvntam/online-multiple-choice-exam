package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.Admin;
import com.fpt.onlineTest.reponsitory.AdminRepository;
import com.fpt.onlineTest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Override
    public boolean isUsernameTaken(String username) {
        Admin existingAdmin = adminRepository.findByUsername(username);
        return existingAdmin != null;
    }

    @Autowired
    AdminRepository adminRepository;

    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    @Override
    public <S extends Admin> S save(S entity) {
        return adminRepository.save(entity);
    }
}

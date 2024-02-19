package com.fpt.onlineTest.service.impl;

import com.fpt.onlineTest.model.BanUser;
import com.fpt.onlineTest.reponsitory.BanUserRepository;
import com.fpt.onlineTest.service.BanUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanUserServiceImpl implements BanUserService {
    @Autowired
    BanUserRepository banUserRepository;

    @Override
    public List<BanUser> getAllBanUser() {
        return banUserRepository.findAll();
    }

    @Override
    public List<BanUser> getBanUserByExamId(Integer examId) {
        return banUserRepository.findBanUserByExamId(examId);
    }

    @Override
    public List<BanUser> getBanUserByUserId(Integer userId) {
        return banUserRepository.findBanUserByUserId(userId);
    }

    @Override
    public List<BanUser> getBanUserByUserIdAndExamId(Integer userId, Integer examId) {
        return banUserRepository.findBanUserByUserIdAndExamId(userId, examId);
    }

    @Override
    public BanUser createBanUser(BanUser banUser) {
        return banUserRepository.save(banUser);
    }

    @Override
    public void deleteBanUser(Integer banUserId) {
        banUserRepository.deleteById(banUserId);
    }
}

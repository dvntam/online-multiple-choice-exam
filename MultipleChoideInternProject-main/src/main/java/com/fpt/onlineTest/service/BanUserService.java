package com.fpt.onlineTest.service;

import com.fpt.onlineTest.model.BanUser;

import java.util.List;

public interface BanUserService {
    //Get all banUser
    List<BanUser> getAllBanUser();

    //Get banUser by examId
    List<BanUser> getBanUserByExamId(Integer examId);

    //Get banUser by userId
    List<BanUser> getBanUserByUserId(Integer userID);

    //Get banUser by userId and examId
    List<BanUser> getBanUserByUserIdAndExamId(Integer userId, Integer examId);

    //Create banUser
    BanUser createBanUser(BanUser banUser);

    //Delete banUser
    void deleteBanUser(Integer banUserId);
}

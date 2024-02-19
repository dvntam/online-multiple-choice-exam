package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.BanUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BanUserRepository extends JpaRepository<BanUser, Integer> {

    @Query(value = "SELECT * FROM ban_user WHERE user_id = ?", nativeQuery = true)
    List<BanUser> findBanUserByUserId(Integer userId);

    @Query(value = "SELECT * FROM ban_user WHERE exam_id = ?", nativeQuery = true)
    List<BanUser> findBanUserByExamId(Integer examId);
    @Query(value = "SELECT * FROM ban_user WHERE user_id = ? AND exam_id = ?", nativeQuery = true)
    List<BanUser> findBanUserByUserIdAndExamId(Integer userId, Integer examId);
}

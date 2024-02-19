package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.UserAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswersRepository extends JpaRepository<UserAnswers, Integer> {
}

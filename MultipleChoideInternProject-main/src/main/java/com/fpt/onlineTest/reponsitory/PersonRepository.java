package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
package com.fpt.onlineTest.reponsitory;

import com.fpt.onlineTest.model.Address;
import com.fpt.onlineTest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}

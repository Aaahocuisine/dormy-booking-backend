package com.dormy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dormy.models.UserInformation;

@Repository
public interface UserRepository extends JpaRepository<UserInformation, Integer> {
	UserInformation findByMobileNo(String mobileNo);
}

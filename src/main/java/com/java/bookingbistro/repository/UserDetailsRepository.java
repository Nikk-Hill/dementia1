package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    UserDetails findByEmailIdAndPassword(String emailId, String password);

    UserDetails findByEmailId(String emailId);

    UserDetails findByPhoneNumber(String phoneNumber);

}

package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.UserDetails;
import com.java.bookingbistro.model.enums.Role;
import com.java.bookingbistro.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntitlementsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public boolean isManagerRole(String userEmailId) {
        UserDetails userDetails = userDetailsRepository.findByEmailId(userEmailId);
        return userDetails.getRole() == Role.MANAGER;
    }

    public boolean isCustomerRole(String userEmailId) {
        UserDetails userDetails = userDetailsRepository.findByEmailId(userEmailId);
        return userDetails.getRole() == Role.CUSTOMER;
    }
}

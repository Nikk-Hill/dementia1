package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.UserDetails;
import com.java.bookingbistro.model.UserDetailsDTO;
import com.java.bookingbistro.repository.UserDetailsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SignUpService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    private final static String EMAIL_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    public String validateUserDetails(UserDetailsDTO userDetails) {
        String exceptionDetails = "";
        if(!isUserNameValid(userDetails.getName())) {
            exceptionDetails = "Name cannot be empty";
        } else if(!isPasswordValid(userDetails.getPassword())) {
            exceptionDetails = "Password cannot be empty";
        } else if(!isEmailIdValid(userDetails.getEmailId())) {
            exceptionDetails = "Email Id is Invalid";
        } else if(!isPhoneNumberValid(userDetails.getPhoneNumber())) {
            exceptionDetails = "Phone Number must be of 10 digits";
        } else if(isAnExistingUser(userDetails.getEmailId(), userDetails.getPhoneNumber())) {
            exceptionDetails = "User Already Exists";
        }
        return exceptionDetails;
    }

    public void signUpUser(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = UserDetails.builder()
                .userName(userDetailsDTO.getName())
                .emailId(userDetailsDTO.getEmailId())
                .password(userDetailsDTO.getPassword())
                .phoneNumber(userDetailsDTO.getPhoneNumber())
                .role(userDetailsDTO.getRole())
                .build();
        userDetailsRepository.save(userDetails);
    }

    private boolean isUserNameValid(String name) {
        return StringUtils.isNotEmpty(name);
    }

    private boolean isPasswordValid(String password) {
        return StringUtils.isNotEmpty(password);
    }

    private boolean isEmailIdValid(String emailId) {
        if (emailId == null) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.length() == 10;
    }

    private boolean isAnExistingUser(String emailId, String phoneNumber) {
        return !Objects.isNull(userDetailsRepository.findByEmailId(emailId)) ||
                !Objects.isNull(userDetailsRepository.findByPhoneNumber(phoneNumber));
    }
}

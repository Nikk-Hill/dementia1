package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.SignUpResponse;
import com.java.bookingbistro.model.UserDetailsDTO;
import com.java.bookingbistro.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/sign-up")
    private SignUpResponse saveUserDetails(@RequestBody UserDetailsDTO userDetails) {
        log.info("Validating User Details : {}", userDetails);
        SignUpResponse signUpResponse = new SignUpResponse();
        String validationResponse = signUpService.validateUserDetails(userDetails);
        if(StringUtils.isEmpty(validationResponse)) {
            log.info("Signing up User : {}", userDetails);
            signUpService.signUpUser(userDetails);
            signUpResponse.setWasSignUpSuccessful(true);
        } else {
            log.error("Sign-up Validations failed --> {}", validationResponse);
            signUpResponse.setWasSignUpSuccessful(false);
            signUpResponse.setExceptionDetails(validationResponse);
        }
        return signUpResponse;
    }
}

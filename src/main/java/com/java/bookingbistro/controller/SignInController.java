package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.SignInResponse;
import com.java.bookingbistro.model.UserDetailsDTO;
import com.java.bookingbistro.model.enums.Role;
import com.java.bookingbistro.service.SignInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/sign-in")
    private SignInResponse signInUser(@RequestBody UserDetailsDTO userDetails) {
        log.info("Signing user in");
        return signInService.signInUser(userDetails);
    }
}

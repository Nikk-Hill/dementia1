package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.UserDetails;
import com.java.bookingbistro.model.SignInResponse;
import com.java.bookingbistro.model.UserDetailsDTO;
import com.java.bookingbistro.repository.UserDetailsRepository;
import com.java.bookingbistro.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SignInService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public SignInResponse signInUser(UserDetailsDTO userDetailsDTO) {
        UserDetails userDetails = userDetailsRepository.findByEmailIdAndPassword(
                userDetailsDTO.getEmailId(),
                userDetailsDTO.getPassword()
        );
        if(userDetails != null) {
            log.info("User Details found : {}", userDetails);
            String userEmailId = userDetails.getEmailId();
            String jwtToken = jwtTokenUtil.generateToken(userEmailId);
            return SignInResponse.builder()
                    .wasSignInSuccessful(true)
                    .userName(userDetails.getUserName())
                    .userEmailId(userEmailId)
                    .jwtToken(jwtToken)
                    .role(userDetails.getRole())
                    .build();
        } else {
            log.error("User Details not found!");
            return SignInResponse.builder()
                    .wasSignInSuccessful(false)
                    .build();
        }
    }
}

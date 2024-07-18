package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponse {
    private boolean wasSignInSuccessful;
    private String userName;
    private String userEmailId;
    private String jwtToken;
    private Role role;
}

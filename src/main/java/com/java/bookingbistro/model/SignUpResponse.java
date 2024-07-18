package com.java.bookingbistro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponse {
    private boolean wasSignUpSuccessful;
    private String exceptionDetails;
}

package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {
    private String name;
    private String emailId;
    private String password;
    private String phoneNumber;
}

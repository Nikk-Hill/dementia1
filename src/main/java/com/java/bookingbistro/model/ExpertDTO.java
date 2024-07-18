package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertDTO {
    private int expertId;
    private String expertName;
    private String location;
    private String workingDays;
    private String workingHours;
    private int timeSlotInterval;
    private Role role;
}

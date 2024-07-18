package com.java.bookingbistro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private int restaurantId;
    private String restaurantName;
    private LocalDate registrationDate;
    private List<String> cuisines;
    private String location;
    private String workingDays;
    private String workingHours;
    private int timeSlotInterval;
    private int capacityPerTimeSlot;
    private List<String> timeSlots;
}

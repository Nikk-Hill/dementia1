package com.java.bookingbistro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESTAURANT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {

    @Id
    @Column(name = "RESTAURANT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    @Column(name = "RESTAURANT_NAME")
    private String restaurantName;

    @Column(name = "REGISTRATION_DATE")
    private LocalDate registrationDate;

    @Column(name = "CUISINES")
    private String cuisines;

    @Column(name = "LOCATION")
    private String location;

    //Comma separated list of DAYS, example, FRI,SAT,SUN
    @Column(name = "WORKING_DAYS")
    private String workingDays;

    //24-HR format, specifying start time to end time, example, 10-22
    @Column(name = "WORKING_HOURS")
    private String workingHours;

    //number of minutes, example, 30, 60 etc
    @Column(name = "TIME_SLOT_INTERVAL")
    private int timeSlotInterval;

    @Column(name = "CAPACITY_PER_TIME_SLOT")
    private int capacityPerTimeSlot;
}

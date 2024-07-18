package com.java.bookingbistro.entity;

import com.java.bookingbistro.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EXPERT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expert {

    @Id
    @Column(name = "EXPERT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expertId;

    @Column(name = "EXPERT_NAME")
    private String expertName;

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

    @Column(name = "ROLE")
    private Role role;
}

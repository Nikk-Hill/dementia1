package com.java.bookingbistro.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "EXPERT_TIME_SLOTS")
@Data
public class ExpertTimeSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EXPERT_ID")
    private Integer expertId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TIME_SLOT")
    private String timeSlot;

}

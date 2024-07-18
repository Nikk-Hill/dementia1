package com.java.bookingbistro.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "RESTAURANT_TIME_SLOTS")
@Data
public class RestaurantTimeSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RESTAURANT_ID")
    private Integer restaurantId;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "TIME_SLOT")
    private String timeSlot;

    @Column(name = "NUMBER_OF_BOOKINGS")
    private int numberOfBookings = 0;
}

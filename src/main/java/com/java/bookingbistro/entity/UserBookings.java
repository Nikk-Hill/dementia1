package com.java.bookingbistro.entity;

import com.java.bookingbistro.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "USER_BOOKINGS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserBookings {

    @Id
    @Column(name = "BOOKING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "EXPERT_ID")
    private Integer expertId;

    @Column(name = "CREATION_DATE")
    private LocalDate creationDate;

    @Column(name = "BOOKING_DATE")
    private LocalDate bookingDate;

    @Column(name = "TIME_SLOT")
    private String timeSlot;
}

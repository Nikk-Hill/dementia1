package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserBookingsDTO {
    private Integer bookingId;
    private String restaurantName;
    private LocalDate creationDate;
    private LocalDate bookingDate;
    private int numberOfPeople;
    private BookingStatus bookingStatus;
    private String timeSlot;
    private long daysRemainingTillBooking;
}

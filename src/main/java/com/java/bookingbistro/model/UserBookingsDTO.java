package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserBookingsDTO {
    private Integer bookingId;
    private String expertName;
    private LocalDate creationDate;
    private LocalDate bookingDate;
    private String timeSlot;
    private long daysRemainingTillBooking;
}

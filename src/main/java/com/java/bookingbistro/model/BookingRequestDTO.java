package com.java.bookingbistro.model;

import com.java.bookingbistro.model.enums.BookingRequestStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingRequestDTO {
    private Integer bookingRequestId;
    private String userEmailId;
    private Integer restaurantId;
    private String restaurantName; //only to display Restaurant Name to Manager
    private String bookingDate;
    private int numberOfPeople;
    private String timeSlot;
    private BookingRequestStatus bookingRequestStatus;
}

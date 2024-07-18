package com.java.bookingbistro.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequestDTO {
    private Integer bookingRequestId;
    private String userEmailId;
    private Integer expertId;
    private String expertName; //only to display Restaurant Name to Manager
    private String bookingDate;
    private String timeSlot;
}

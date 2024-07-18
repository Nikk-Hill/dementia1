package com.java.bookingbistro.entity;

import com.java.bookingbistro.model.enums.BookingRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BOOKING_REQUEST")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    @Id
    @Column(name = "BOOKING_REQUEST_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingRequestId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "BOOKING_ID")
    private Integer bookingId;

    @Column(name = "STATUS")
    private BookingRequestStatus status;
}

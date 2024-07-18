package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.BookingRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRequestRepository extends JpaRepository<BookingRequest, Integer> {

    BookingRequest findByBookingId(Integer bookingId);
}

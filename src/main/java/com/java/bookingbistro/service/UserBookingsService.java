package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.BookingRequest;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.model.UserBookingsDTO;
import com.java.bookingbistro.model.enums.BookingStatus;
import com.java.bookingbistro.repository.BookingRequestRepository;
import com.java.bookingbistro.repository.RestaurantRepository;
import com.java.bookingbistro.repository.UserBookingsRepository;
import com.java.bookingbistro.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserBookingsService {

    @Autowired
    private UserBookingsRepository userBookingsRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Transactional
    public void cancelBooking(Integer bookingId) {
        markUserBookingAsCancelled(bookingId);
        deleteBookingRequestEntry(bookingId);
    }

    private void markUserBookingAsCancelled(Integer bookingId) {
        log.info("Cancelling the booking with id : {}", bookingId);
        UserBookings booking = userBookingsRepository.findById(bookingId).get();
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }

    private void deleteBookingRequestEntry(Integer bookingId) {
        log.info("Deleting Booking Request for bookingId : {}", bookingId);
        BookingRequest bookingRequest = bookingRequestRepository.findByBookingId(bookingId);
        bookingRequestRepository.deleteById(bookingRequest.getBookingRequestId());
    }

    public List<UserBookingsDTO> getAllBookingsForUser(String userEmailId) {
        Integer userId = userDetailsRepository.findByEmailId(userEmailId).getUserId();
        List<UserBookings> userBookings = userBookingsRepository.findByUserId(userId);
        return userBookings.stream().map(booking -> UserBookingsDTO.builder()
                .bookingId(booking.getBookingId())
                .restaurantName(restaurantRepository.findById(
                        booking.getRestaurantId()).get().getRestaurantName())
                .creationDate(booking.getCreationDate())
                .bookingDate(booking.getBookingDate())
                .numberOfPeople(booking.getNumberOfPeople())
                .bookingStatus(booking.getBookingStatus())
                .timeSlot(booking.getTimeSlot())
                .daysRemainingTillBooking(calculateDaysRemainingTillBooking(booking.getBookingDate()))
                .build()
        ).collect(Collectors.toList());
    }

    private long calculateDaysRemainingTillBooking(LocalDate bookingDate) {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(today, bookingDate);
    }

}

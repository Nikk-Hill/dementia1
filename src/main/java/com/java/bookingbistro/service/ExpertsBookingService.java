package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.BookingRequest;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.model.UserBookingsDTO;
import com.java.bookingbistro.repository.BookingRequestRepository;
import com.java.bookingbistro.repository.ExpertRepository;
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
public class ExpertsBookingService {

    @Autowired
    private UserBookingsRepository userBookingsRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private SlotsBookingService slotsBookingService;

    public void createBooking(BookingRequestDTO bookingRequestDTO) {
        Integer userId = userDetailsRepository.findByEmailId(bookingRequestDTO.getUserEmailId()).getUserId();
        UserBookings userBooking = UserBookings.builder()
                .userId(userId)
                .expertId(bookingRequestDTO.getExpertId())
                .creationDate(LocalDate.now())
                .bookingDate(LocalDate.parse(bookingRequestDTO.getBookingDate()))
                .timeSlot(bookingRequestDTO.getTimeSlot())
                .build();
        slotsBookingService.bookExpertForTimeSlot(userBooking);
        userBookingsRepository.save(userBooking);
        log.info("User booking created successfully {}", userBooking);
    }

    @Transactional
    public void cancelBooking(Integer bookingId) {
        markUserBookingAsCancelled(bookingId);
        deleteBookingRequestEntry(bookingId);
    }

    private void markUserBookingAsCancelled(Integer bookingId) {
        log.info("Cancelling the booking with id : {}", bookingId);
        UserBookings booking = userBookingsRepository.findById(bookingId).get();
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
                .expertName(expertRepository.findById(
                        booking.getExpertId()).get().getExpertName())
                .creationDate(booking.getCreationDate())
                .bookingDate(booking.getBookingDate())
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

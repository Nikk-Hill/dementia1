package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.BookingRequest;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.model.enums.BookingRequestStatus;
import com.java.bookingbistro.model.enums.BookingStatus;
import com.java.bookingbistro.repository.BookingRequestRepository;
import com.java.bookingbistro.repository.RestaurantRepository;
import com.java.bookingbistro.repository.UserBookingsRepository;
import com.java.bookingbistro.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingRequestsService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private UserBookingsRepository userBookingsRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableBookingService tableBookingService;

    public void createBooking(BookingRequestDTO bookingRequestDTO) {
        UserBookings userBooking = insertRecordInUserBookings(bookingRequestDTO);
        insertRecordInBookingRequest(bookingRequestDTO, userBooking);
    }

    private UserBookings insertRecordInUserBookings(BookingRequestDTO bookingRequestDTO) {
        Integer userId = userDetailsRepository.findByEmailId(bookingRequestDTO.getUserEmailId()).getUserId();
        UserBookings userBooking = UserBookings.builder()
                .userId(userId)
                .restaurantId(bookingRequestDTO.getRestaurantId())
                .creationDate(LocalDate.now())
                .bookingDate(LocalDate.parse(bookingRequestDTO.getBookingDate()))
                .numberOfPeople(bookingRequestDTO.getNumberOfPeople())
                .timeSlot(bookingRequestDTO.getTimeSlot())
                .bookingStatus(BookingStatus.PENDING)
                .build();
        return userBookingsRepository.save(userBooking);
    }

    private void insertRecordInBookingRequest(BookingRequestDTO bookingRequestDTO,
                                              UserBookings userBooking) {
        BookingRequest bookingRequest = BookingRequest.builder()
                .bookingId(userBooking.getBookingId())
                .userName(bookingRequestDTO.getUserEmailId())
                .status(BookingRequestStatus.PENDING)
                .build();
        bookingRequestRepository.save(bookingRequest);
    }

    public List<BookingRequestDTO> getAllBookingRequests() {
        List<BookingRequest> bookingRequests = bookingRequestRepository.findAll();
        List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
        for(BookingRequest bookingRequest : bookingRequests) {
            UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
            bookingRequestDTOs.add(
                    BookingRequestDTO.builder()
                            .bookingRequestId(bookingRequest.getBookingRequestId())
                            .userEmailId(bookingRequest.getUserName())
                            .restaurantId(userBooking.getRestaurantId())
                            .restaurantName(restaurantRepository.findById(userBooking.getRestaurantId())
                                    .get().getRestaurantName())
                            .bookingDate(String.valueOf(userBooking.getBookingDate()))
                            .numberOfPeople(userBooking.getNumberOfPeople())
                            .timeSlot(userBooking.getTimeSlot())
                            .bookingRequestStatus(bookingRequest.getStatus())
                            .build()
            );
        }
        return bookingRequestDTOs;
    }

    @Transactional
    public void approveBookingRequest(Integer bookingRequestId) {
        bookATableForGivenTimeSlot(bookingRequestId);
        markBookingRequestAsApproved(bookingRequestId);
        markUserBookingAsConfirmed(bookingRequestId);
    }

    private void bookATableForGivenTimeSlot(Integer bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
        UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
        tableBookingService.bookTableForTimeSlot(userBooking);
    }

    private void markBookingRequestAsApproved(Integer bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
        bookingRequest.setStatus(BookingRequestStatus.APPROVED);
    }

    private void markUserBookingAsConfirmed(Integer bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
        UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
        userBooking.setBookingStatus(BookingStatus.CONFIRMED);
    }

    @Transactional
    public void rejectBookingRequest(Integer bookingRequestId) {
        markBookingRequestAsRejected(bookingRequestId);
        markUserBookingAsRejected(bookingRequestId);
    }

    private void markBookingRequestAsRejected(Integer bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
        bookingRequest.setStatus(BookingRequestStatus.REJECTED);
    }

    private void markUserBookingAsRejected(Integer bookingRequestId) {
        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
        UserBookings userBookings = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
        userBookings.setBookingStatus(BookingStatus.REJECTED);    }
}

package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.BookingRequest;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.model.enums.BookingRequestStatus;
import com.java.bookingbistro.model.enums.BookingStatus;
import com.java.bookingbistro.repository.BookingRequestRepository;
import com.java.bookingbistro.repository.ExpertRepository;
import com.java.bookingbistro.repository.UserBookingsRepository;
import com.java.bookingbistro.repository.UserDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Slf4j
public class BookingRequestsService {

    @Autowired
    private BookingRequestRepository bookingRequestRepository;

    @Autowired
    private UserBookingsRepository userBookingsRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private SlotsBookingService slotsBookingService;

//    public void createBooking(BookingRequestDTO bookingRequestDTO) {
//        Integer userId = userDetailsRepository.findByEmailId(bookingRequestDTO.getUserEmailId()).getUserId();
//        UserBookings userBooking = UserBookings.builder()
//                .userId(userId)
//                .expertId(bookingRequestDTO.getExpertId())
//                .creationDate(LocalDate.now())
//                .bookingDate(LocalDate.parse(bookingRequestDTO.getBookingDate()))
//                .timeSlot(bookingRequestDTO.getTimeSlot())
//                .build();
//        UserBookings userBookings = userBookingsRepository.save(userBooking);
//        log.info("User booking created successfully {}", userBookings);
//    }

//    private void insertRecordInBookingRequest(BookingRequestDTO bookingRequestDTO,
//                                              UserBookings userBooking) {
//        BookingRequest bookingRequest = BookingRequest.builder()
//                .bookingId(userBooking.getBookingId())
//                .userName(bookingRequestDTO.getUserEmailId())
//                .status(BookingRequestStatus.PENDING)
//                .build();
//        bookingRequestRepository.save(bookingRequest);
//    }

//    public List<BookingRequestDTO> getAllBookingRequests() {
//        List<BookingRequest> bookingRequests = bookingRequestRepository.findAll();
//        List<BookingRequestDTO> bookingRequestDTOs = new ArrayList<>();
//        for(BookingRequest bookingRequest : bookingRequests) {
//            UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
//            bookingRequestDTOs.add(
//                    BookingRequestDTO.builder()
//                            .bookingRequestId(bookingRequest.getBookingRequestId())
//                            .userEmailId(bookingRequest.getUserName())
//                            .expertId(userBooking.getExpertId())
//                            .expertName(expertRepository.findById(userBooking.getExpertId())
//                                    .get().getExpertName())
//                            .bookingDate(String.valueOf(userBooking.getBookingDate()))
//                            .timeSlot(userBooking.getTimeSlot())
//                            .build()
//            );
//        }
//        return bookingRequestDTOs;
//    }

//    @Transactional
//    public void approveBookingRequest(Integer bookingRequestId) {
//        bookATableForGivenTimeSlot(bookingRequestId);
//        markBookingRequestAsApproved(bookingRequestId);
//        markUserBookingAsConfirmed(bookingRequestId);
//    }
//
//    private void bookATableForGivenTimeSlot(Integer bookingRequestId) {
//        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
//        UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
//        slotsBookingService.bookExpertForTimeSlot(userBooking);
//    }
//
//    private void markBookingRequestAsApproved(Integer bookingRequestId) {
//        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
//        bookingRequest.setStatus(BookingRequestStatus.APPROVED);
//    }
//
//    private void markUserBookingAsConfirmed(Integer bookingRequestId) {
//        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
//        UserBookings userBooking = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
//        userBooking.setBookingStatus(BookingStatus.CONFIRMED);
//    }
//
//    @Transactional
//    public void rejectBookingRequest(Integer bookingRequestId) {
//        markBookingRequestAsRejected(bookingRequestId);
//        markUserBookingAsRejected(bookingRequestId);
//    }
//
//    private void markBookingRequestAsRejected(Integer bookingRequestId) {
//        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
//        bookingRequest.setStatus(BookingRequestStatus.REJECTED);
//    }
//
//    private void markUserBookingAsRejected(Integer bookingRequestId) {
//        BookingRequest bookingRequest = bookingRequestRepository.findById(bookingRequestId).get();
//        UserBookings userBookings = userBookingsRepository.findById(bookingRequest.getBookingId()).get();
//        userBookings.setBookingStatus(BookingStatus.REJECTED);    }
}

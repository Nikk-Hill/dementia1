package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.service.BookingRequestsService;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-request")
@CrossOrigin(origins = "*")
public class BookingRequestController {

    @Autowired
    private BookingRequestsService bookingRequestsService;

//    @PostMapping("/create")
//    public void createBooking(@RequestBody BookingRequestDTO bookingRequest) {
//            bookingRequest.setUserEmailId(SecurityUtils.getUsername());
//            bookingRequestsService.createBooking(bookingRequest);
//    }

//    @GetMapping("/all")
//    public List<BookingRequestDTO> getAllBookingRequests() {
//            return bookingRequestsService.getAllBookingRequests();
//
//    }

//    @GetMapping("/approve")
//    public void approveBookingRequest(@RequestParam Integer bookingRequestId) {
//            bookingRequestsService.approveBookingRequest(bookingRequestId);
//    }
//
//    @GetMapping("/reject")
//    public void rejectBookingRequest(@RequestParam Integer bookingRequestId) {
//            bookingRequestsService.rejectBookingRequest(bookingRequestId);
//
//    }
}

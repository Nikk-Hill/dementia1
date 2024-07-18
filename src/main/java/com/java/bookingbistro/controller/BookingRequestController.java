package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.service.BookingRequestsService;
import com.java.bookingbistro.service.EntitlementsService;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking-request")
@CrossOrigin(origins = "*")
public class BookingRequestController {

    @Autowired
    private EntitlementsService entitlementsService;

    @Autowired
    private BookingRequestsService bookingRequestsService;

    @PostMapping("/create")
    public void createBooking(@RequestBody BookingRequestDTO bookingRequest) {
        if (entitlementsService.isCustomerRole(SecurityUtils.getUsername())) {
            bookingRequest.setUserEmailId(SecurityUtils.getUsername());
            bookingRequestsService.createBooking(bookingRequest);
        }
    }

    @GetMapping("/all")
    public List<BookingRequestDTO> getAllBookingRequests() {
        if (entitlementsService.isManagerRole(SecurityUtils.getUsername())) {
            return bookingRequestsService.getAllBookingRequests();
        }
        return null;
    }

    @GetMapping("/approve")
    public void approveBookingRequest(@RequestParam Integer bookingRequestId) {
        if (entitlementsService.isManagerRole(SecurityUtils.getUsername())) {
            bookingRequestsService.approveBookingRequest(bookingRequestId);
        }
    }

    @GetMapping("/reject")
    public void rejectBookingRequest(@RequestParam Integer bookingRequestId) {
        if (entitlementsService.isManagerRole(SecurityUtils.getUsername())) {
            bookingRequestsService.rejectBookingRequest(bookingRequestId);
        }
    }
}

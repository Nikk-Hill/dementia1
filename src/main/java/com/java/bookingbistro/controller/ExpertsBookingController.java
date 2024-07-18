package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.BookingRequestDTO;
import com.java.bookingbistro.model.UserBookingsDTO;
import com.java.bookingbistro.service.ExpertsBookingService;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpertsBookingController {

    @Autowired
    private ExpertsBookingService expertsBookingService;

    @PostMapping("/create")
    public void createBooking(@RequestBody BookingRequestDTO bookingRequest) {
        bookingRequest.setUserEmailId(SecurityUtils.getUsername());
        expertsBookingService.createBooking(bookingRequest);
    }

    @GetMapping("/all")
    public List<UserBookingsDTO> fetchAllBookings() {
        System.out.println("Fetching all user-bookings");
        String userEmailId = SecurityUtils.getUsername();
        return expertsBookingService.getAllBookingsForUser(userEmailId);
    }

    @GetMapping("/cancel")
    public String cancelBooking(@RequestParam Integer bookingId) {
        try {
            expertsBookingService.cancelBooking(bookingId);
            return "SUCCESS";
        } catch (Exception exception) {
            return "FAILURE";
        }
    }
}

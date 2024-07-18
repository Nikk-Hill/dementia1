package com.java.bookingbistro.controller;

import com.java.bookingbistro.model.UserBookingsDTO;
import com.java.bookingbistro.service.EntitlementsService;
import com.java.bookingbistro.service.UserBookingsService;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200")
public class UserBookingsController {

    @Autowired
    private UserBookingsService userBookingsService;

    @Autowired
    private EntitlementsService entitlementsService;

    @GetMapping("/all")
    public List<UserBookingsDTO> fetchAllBookings() {
        System.out.println("Fetching all user-bookings");
        String userEmailId = SecurityUtils.getUsername();
        return userBookingsService.getAllBookingsForUser(userEmailId);
    }

    @GetMapping("/cancel")
    public String cancelBooking(@RequestParam Integer bookingId) {
        try {
            if(entitlementsService.isCustomerRole(SecurityUtils.getUsername())) {
                userBookingsService.cancelBooking(bookingId);
                return "SUCCESS";
            } else {
                throw new RuntimeException("User is not entitled to perform this Operation");
            }
        } catch (Exception exception) {
            return "FAILURE";
        }
    }
}

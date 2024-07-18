package com.java.bookingbistro.controller;

import com.java.bookingbistro.entity.RestaurantTimeSlots;
import com.java.bookingbistro.repository.RestaurantTimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Controller for testing purpose only
@RestController
@CrossOrigin(origins = "*")
public class RestaurantTimeSlotsController {

    @Autowired
    private RestaurantTimeSlotsRepository restaurantTimeSlotsRepository;

    @GetMapping("/restaurant-time-slots")
    public List<RestaurantTimeSlots> getAllRestaurantTimeSlots() {
        return restaurantTimeSlotsRepository.findAll();
    }
}

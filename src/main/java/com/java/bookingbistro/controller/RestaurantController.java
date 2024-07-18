package com.java.bookingbistro.controller;

import com.java.bookingbistro.entity.Restaurant;
import com.java.bookingbistro.model.RestaurantDTO;
import com.java.bookingbistro.service.EntitlementsService;
import com.java.bookingbistro.service.RestaurantService;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private EntitlementsService entitlementsService;

    @PostMapping("/add")
    public void createRestaurant(@RequestBody Restaurant restaurant) {
        if(entitlementsService.isManagerRole(SecurityUtils.getUsername())) {
            restaurantService.addNewRestaurant(restaurant);
        } else {
            throw new RuntimeException("User is not entitled to perform this Operation");
        }
    }

    @GetMapping("/all")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/location")
    public List<RestaurantDTO> getAllRestaurantsForLocation(@RequestParam String location) {
        return restaurantService.getAllRestaurantsForLocation(location);
    }
}

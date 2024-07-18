package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.Restaurant;
import com.java.bookingbistro.model.RestaurantDTO;
import com.java.bookingbistro.repository.RestaurantRepository;
import com.java.bookingbistro.service.util.TimeSlotsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addNewRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(restaurant -> RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .registrationDate(restaurant.getRegistrationDate())
                .cuisines(Arrays.asList(restaurant.getCuisines().split(",")))
                .location(restaurant.getLocation())
                .timeSlots(TimeSlotsUtil.getTimeSlotsForRestaurant(
                        restaurant.getWorkingHours(),
                        restaurant.getTimeSlotInterval()))
                .build()
        ).collect(Collectors.toList());
    }

    public List<RestaurantDTO> getAllRestaurantsForLocation(String location) {
        List<Restaurant> restaurants = restaurantRepository.findByLocation(location);
        return restaurants.stream().map(restaurant -> RestaurantDTO.builder()
                .restaurantId(restaurant.getRestaurantId())
                .restaurantName(restaurant.getRestaurantName())
                .registrationDate(restaurant.getRegistrationDate())
                .cuisines(Arrays.asList(restaurant.getCuisines().split(",")))
                .location(restaurant.getLocation())
                .timeSlots(TimeSlotsUtil.getTimeSlotsForRestaurant(
                        restaurant.getWorkingHours(),
                        restaurant.getTimeSlotInterval()))
                .build()
        ).collect(Collectors.toList());
    }
}

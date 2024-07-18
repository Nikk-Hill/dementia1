package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

    List<Restaurant> findByLocation(String location);

    Restaurant findByRestaurantName(String restaurantName);
}

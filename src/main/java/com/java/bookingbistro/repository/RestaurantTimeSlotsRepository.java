package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.RestaurantTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RestaurantTimeSlotsRepository extends JpaRepository<RestaurantTimeSlots, Integer> {

    RestaurantTimeSlots findByRestaurantIdAndDateAndTimeSlot(Integer restaurantId, LocalDate date, String timeSlot);
}

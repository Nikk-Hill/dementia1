package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.UserBookings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBookingsRepository extends JpaRepository<UserBookings, Integer> {

    List<UserBookings> findByUserId(Integer userId);
}

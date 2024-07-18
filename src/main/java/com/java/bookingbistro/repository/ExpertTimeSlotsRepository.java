package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.ExpertTimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ExpertTimeSlotsRepository extends JpaRepository<ExpertTimeSlots, Integer> {

    ExpertTimeSlots findByExpertIdAndDateAndTimeSlot(Integer expertId, LocalDate date, String timeSlot);

}

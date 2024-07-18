package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.DailyMedicineReminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyMedicineReminderRepository extends JpaRepository<DailyMedicineReminder, Integer> {

    DailyMedicineReminder findByUserId(Integer userId);
}

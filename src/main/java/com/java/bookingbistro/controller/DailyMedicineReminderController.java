package com.java.bookingbistro.controller;

import com.java.bookingbistro.entity.DailyMedicineReminder;
import com.java.bookingbistro.repository.DailyMedicineReminderRepository;
import com.java.bookingbistro.repository.UserDetailsRepository;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daily-medicine-reminder")
@CrossOrigin(origins = "*")
public class DailyMedicineReminderController {

    @Autowired
    private DailyMedicineReminderRepository dailyMedicineReminderRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @PostMapping("/save")
    public void saveDailyMedicineReminder(@RequestBody DailyMedicineReminder
                                                      dailyMedicineReminder) {
        dailyMedicineReminderRepository.save(dailyMedicineReminder);
    }

    @GetMapping("/get")
    public DailyMedicineReminder getDailyMedicineReminder() {
        String userEmailId = SecurityUtils.getUsername();
        Integer userId = userDetailsRepository.findByEmailId(userEmailId).getUserId();
        return dailyMedicineReminderRepository.findByUserId(userId);
    }
}

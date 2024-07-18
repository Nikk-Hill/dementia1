package com.java.bookingbistro.controller;

import com.java.bookingbistro.service.ExpertsTimeSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ExpertTimeSlotsController {

    @Autowired
    private ExpertsTimeSlotsService expertsTimeSlotsService;

    @GetMapping("/expert-time-slots")
    public List<String> getAvailableExpertTimeSlots(@RequestParam Integer expertId,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return expertsTimeSlotsService.getAvailableTimeSlotsForExpertOnDate(expertId, date);
    }

}

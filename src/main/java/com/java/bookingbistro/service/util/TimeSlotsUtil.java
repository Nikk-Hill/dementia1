package com.java.bookingbistro.service.util;

import com.java.bookingbistro.entity.Expert;
import com.java.bookingbistro.repository.ExpertTimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotsUtil {

    @Autowired
    private ExpertTimeSlotsRepository expertTimeSlotsRepository;

    public List<String> getTimeSlotsForExpert(Expert expert, LocalDate date) {
        String workingHours = expert.getWorkingHours();
        LocalTime openingTime = getOpeningTime(workingHours);
        LocalTime closingTime = getClosingTime(workingHours);

        List<String> timeSlots = new ArrayList<>();
        LocalTime currentTime = openingTime;
        while (currentTime.isBefore(closingTime)) {
            LocalTime nextTime = currentTime.plusMinutes(30);
            if (nextTime.isAfter(closingTime)) {
                break;
            }
            String timeSlot = currentTime + "-" + nextTime;
            if(isTimeSlotAvailable(expert.getExpertId(), date, timeSlot)) {
                timeSlots.add(timeSlot);
            }
            currentTime = nextTime;
        }
        return timeSlots;
    }

    private static LocalTime getOpeningTime(String workingHours) {
        String[] hours = workingHours.split("-");
        return LocalTime.of(Integer.parseInt(hours[0]), 0);
    }

    private static LocalTime getClosingTime(String workingHours) {
        String[] hours = workingHours.split("-");
        return LocalTime.of(Integer.parseInt(hours[1]), 0);
    }

    private boolean isTimeSlotAvailable(Integer expertId, LocalDate date, String timeSlot) {
        return expertTimeSlotsRepository.findByExpertIdAndDateAndTimeSlot(
                expertId, date, timeSlot) == null;
    }
}

package com.java.bookingbistro.service.util;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotsUtil {

    public static List<String> getTimeSlotsForRestaurant(String workingHours,
                                                         int timeSlotIntervalInMinutes) {
        LocalTime openingTime = getOpeningTime(workingHours);
        LocalTime closingTime = getClosingTime(workingHours);

        List<String> timeSlots = new ArrayList<>();
        LocalTime currentTime = openingTime;
        while (currentTime.isBefore(closingTime)) {
            LocalTime nextTime = currentTime.plusMinutes(timeSlotIntervalInMinutes);
            if (nextTime.isAfter(closingTime)) {
                break;
            }
            timeSlots.add(currentTime + "-" + nextTime);
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
}

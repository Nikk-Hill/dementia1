package com.java.bookingbistro;

import com.java.bookingbistro.service.util.TimeSlotsUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class TimeSlotsUtilTest {

    @Test
    public void getTimeSlotsForRestaurantTest() {
        String workingHours = "10-22";
        int timeSlotIntervalInMinutes = 60;
        List<String> timeSlots = TimeSlotsUtil.getTimeSlotsForRestaurant(workingHours,
                timeSlotIntervalInMinutes);
        log.info(timeSlots.toString());
    }
}

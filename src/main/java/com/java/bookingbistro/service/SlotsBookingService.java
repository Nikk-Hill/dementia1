package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.Expert;
import com.java.bookingbistro.entity.ExpertTimeSlots;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.repository.ExpertRepository;
import com.java.bookingbistro.repository.ExpertTimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlotsBookingService {

    @Autowired
    private ExpertTimeSlotsRepository expertTimeSlotsRepository;

    @Autowired
    private ExpertRepository expertRepository;

    public void bookExpertForTimeSlot(UserBookings booking) {
        Expert expert = expertRepository.findById(booking.getExpertId()).get();
        ExpertTimeSlots expertTimeSlots = expertTimeSlotsRepository.findByExpertIdAndDateAndTimeSlot(
                booking.getExpertId(), booking.getBookingDate(), booking.getTimeSlot());
        if(expertTimeSlots != null) {
            throw new RuntimeException("Time Slot not available!");
        } else {
            insertRecordInExpertTimeSlots(booking, expert);
        }
    }

    private void insertRecordInExpertTimeSlots(UserBookings booking,
                                               Expert expert) {
        ExpertTimeSlots expertTimeSlots = new ExpertTimeSlots();
        expertTimeSlots.setExpertId(expert.getExpertId());
        expertTimeSlots.setDate(booking.getBookingDate());
        expertTimeSlots.setTimeSlot(booking.getTimeSlot());
        expertTimeSlotsRepository.save(expertTimeSlots);
    }

    /*@Transactional
    private void updateRestaurantTimeSlots(UserBookings booking,
                                           Expert expert,
                                           ExpertTimeSlots restaurantTimeSlot) {
        if (canTimeSlotAccommodateTheBooking(expert.getCapacityPerTimeSlot(),
                booking.getNumberOfPeople(),
                restaurantTimeSlot.getNumberOfBookings())) {
            updateNumberOfBookingsForTimeSlot(restaurantTimeSlot, booking.getNumberOfPeople());
        } else {
            throw new RuntimeException("Time Slot cannot accommodate these many people");
        }
    }

    private void updateNumberOfBookingsForTimeSlot(ExpertTimeSlots restaurantTimeSlot,
                                                   int numberOfPeople) {
        restaurantTimeSlot.setNumberOfBookings(
                restaurantTimeSlot.getNumberOfBookings() + numberOfPeople
        );
    }

    private boolean canTimeSlotAccommodateTheBooking(int capacity,
                                                     int numberOfPeople,
                                                     int currentNumberOfBookings) {
        return currentNumberOfBookings + numberOfPeople <= capacity;
    }*/
}

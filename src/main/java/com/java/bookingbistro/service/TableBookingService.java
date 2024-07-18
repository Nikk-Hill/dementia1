package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.Restaurant;
import com.java.bookingbistro.entity.RestaurantTimeSlots;
import com.java.bookingbistro.entity.UserBookings;
import com.java.bookingbistro.repository.RestaurantRepository;
import com.java.bookingbistro.repository.RestaurantTimeSlotsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TableBookingService {

    @Autowired
    private RestaurantTimeSlotsRepository restaurantTimeSlotsRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void bookTableForTimeSlot(UserBookings booking) {
        Restaurant restaurant = restaurantRepository.findById(booking.getRestaurantId()).get();
        RestaurantTimeSlots restaurantTimeSlots = restaurantTimeSlotsRepository.findByRestaurantIdAndDateAndTimeSlot(
                booking.getRestaurantId(), booking.getBookingDate(), booking.getTimeSlot());
        if(restaurantTimeSlots != null) {
            updateRestaurantTimeSlots(booking, restaurant, restaurantTimeSlots);
        } else {
            insertRecordInRestaurantTimeSlots(booking, restaurant);
        }
    }

    private void insertRecordInRestaurantTimeSlots(UserBookings booking,
                                                   Restaurant restaurant) {
        RestaurantTimeSlots restaurantTimeSlots = new RestaurantTimeSlots();
        restaurantTimeSlots.setRestaurantId(restaurant.getRestaurantId());
        restaurantTimeSlots.setDate(booking.getBookingDate());
        restaurantTimeSlots.setNumberOfBookings(booking.getNumberOfPeople());
        restaurantTimeSlots.setTimeSlot(booking.getTimeSlot());
        restaurantTimeSlotsRepository.save(restaurantTimeSlots);
    }

    @Transactional
    private void updateRestaurantTimeSlots(UserBookings booking,
                                           Restaurant restaurant,
                                           RestaurantTimeSlots restaurantTimeSlot) {
        if (canTimeSlotAccommodateTheBooking(restaurant.getCapacityPerTimeSlot(),
                booking.getNumberOfPeople(),
                restaurantTimeSlot.getNumberOfBookings())) {
            updateNumberOfBookingsForTimeSlot(restaurantTimeSlot, booking.getNumberOfPeople());
        } else {
            throw new RuntimeException("Time Slot cannot accommodate these many people");
        }
    }

    private void updateNumberOfBookingsForTimeSlot(RestaurantTimeSlots restaurantTimeSlot,
                                                   int numberOfPeople) {
        restaurantTimeSlot.setNumberOfBookings(
                restaurantTimeSlot.getNumberOfBookings() + numberOfPeople
        );
    }

    private boolean canTimeSlotAccommodateTheBooking(int capacity,
                                                     int numberOfPeople,
                                                     int currentNumberOfBookings) {
        return currentNumberOfBookings + numberOfPeople <= capacity;
    }
}

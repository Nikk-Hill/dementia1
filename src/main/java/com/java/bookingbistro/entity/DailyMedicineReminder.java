package com.java.bookingbistro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "DAILY_MEDICINE_REMINDER")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DailyMedicineReminder {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "MORNING_REMINDER_TIME")
    private LocalTime morningReminderTime;

    @Column(name = "EVENING_REMINDER_TIME")
    private LocalTime eveningReminderTime;
}

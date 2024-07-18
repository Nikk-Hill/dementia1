package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.Expert;
import com.java.bookingbistro.repository.ExpertRepository;
import com.java.bookingbistro.service.util.TimeSlotsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpertsTimeSlotsService {

    @Autowired
    private ExpertRepository expertRepository;

    @Autowired
    private TimeSlotsUtil timeSlotsUtil;

    public List<String> getAvailableTimeSlotsForExpertOnDate(Integer expertId,
                                                             LocalDate date) {
        Expert expert = expertRepository.findById(expertId).get();
        return timeSlotsUtil.getTimeSlotsForExpert(expert, date);
    }
}

package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.Expert;
import com.java.bookingbistro.model.ExpertDTO;
import com.java.bookingbistro.model.enums.Role;
import com.java.bookingbistro.repository.ExpertRepository;
import com.java.bookingbistro.service.util.TimeSlotsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpertService {

    @Autowired
    private ExpertRepository expertRepository;

    public void addNewExpert(Expert expert) {
        expertRepository.save(expert);
    }

    public List<ExpertDTO> getAllDoctors() {
        List<Expert> experts = expertRepository.findAll();
        return experts.stream()
                .filter(expert -> Role.DOCTOR.equals(expert.getRole()))
                .map(expert -> ExpertDTO.builder()
                                .expertId(expert.getExpertId())
                                .expertName(expert.getExpertName())
                                .location(expert.getLocation())
                                .workingDays(expert.getWorkingDays())
                                .workingHours(expert.getWorkingHours())
//                .timeSlots(TimeSlotsUtil.getTimeSlotsForExpert(
//                        expert.getWorkingHours(),
//                        expert.getTimeSlotInterval()))

                                .role(expert.getRole())
                                .build()
                ).collect(Collectors.toList());
    }

    public List<ExpertDTO> getAllNurses() {
        List<Expert> experts = expertRepository.findAll();
        return experts.stream()
                .filter(expert -> Role.NURSE.equals(expert.getRole()))
                .map(expert -> ExpertDTO.builder()
                                .expertId(expert.getExpertId())
                                .expertName(expert.getExpertName())
                                .location(expert.getLocation())
//                        .timeSlots(TimeSlotsUtil.getTimeSlotsForExpert(
//                                expert.getWorkingHours(),
//                                expert.getTimeSlotInterval()))
                                .role(expert.getRole())
                                .build()
                ).collect(Collectors.toList());
    }
}

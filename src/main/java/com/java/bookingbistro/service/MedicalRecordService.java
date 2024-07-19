package com.java.bookingbistro.service;

import com.java.bookingbistro.entity.MedicalRecord;
import com.java.bookingbistro.repository.MedicalRecordRepository;
import com.java.bookingbistro.repository.UserDetailsRepository;
import com.java.bookingbistro.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public MedicalRecord saveMedicalRecord(MultipartFile file) throws IOException {
        MedicalRecord document = new MedicalRecord();
        document.setName(file.getOriginalFilename());
        document.setData(file.getBytes());
        document.setUserId(getUserId());
        return medicalRecordRepository.save(document);
    }

    public MedicalRecord getMedicalRecord() {
        Integer userId = getUserId();
        return medicalRecordRepository.findByUserId(userId);
    }

    private Integer getUserId() {
        String userEmailId = SecurityUtils.getUsername();
        return userDetailsRepository.findByEmailId(userEmailId).getUserId();
    }

}

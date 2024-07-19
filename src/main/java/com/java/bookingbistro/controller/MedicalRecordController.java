package com.java.bookingbistro.controller;

import com.java.bookingbistro.entity.MedicalRecord;
import com.java.bookingbistro.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/medical-record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMedicalRecord(@RequestParam("file") MultipartFile file) throws IOException {
        MedicalRecord medicalRecord = medicalRecordService.saveMedicalRecord(file);
        return ResponseEntity.ok("Document uploaded successfully. ID: " + medicalRecord.getId());
    }

    @GetMapping("/get")
    public byte[] downloadMedicalRecord() {
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord();
        return medicalRecord.getData();
    }
}

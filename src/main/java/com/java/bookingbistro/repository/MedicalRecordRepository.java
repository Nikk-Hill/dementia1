package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    MedicalRecord findByUserId(Integer userId);
}

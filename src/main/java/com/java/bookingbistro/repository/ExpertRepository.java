package com.java.bookingbistro.repository;

import com.java.bookingbistro.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert, Integer> {

    List<Expert> findByLocation(String location);

    Expert findByExpertName(String expertName);
}

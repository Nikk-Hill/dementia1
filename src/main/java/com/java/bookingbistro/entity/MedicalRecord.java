package com.java.bookingbistro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEDICAL_RECORD")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicalRecord {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "RECORD_NAME")
    private String name;

    @Column(name = "RECORD")
    private byte[] data;
}

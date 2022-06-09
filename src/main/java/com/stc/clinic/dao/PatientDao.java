package com.stc.clinic.dao;

import com.stc.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDao extends JpaRepository<Patient, Long> {

    Patient findOneByPhone(String phone);
}
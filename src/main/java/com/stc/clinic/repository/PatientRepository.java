package com.stc.clinic.repository;

import com.stc.clinic.dao.PatientDao;
import com.stc.clinic.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class PatientRepository {

    @Autowired
    private PatientDao dao;

    public Patient getPatientByPhone(String phone){
        return dao.findOneByPhone(phone);
    }

    public void savePatient(Patient patient){
        patient.setUpdateDate(new Date());
        dao.save(patient);
    }
}
package com.stc.clinic.service;

import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.entity.Patient;
import com.stc.clinic.mapper.PatientMapper;
import com.stc.clinic.repository.PatientRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PatientService {

    private PatientMapper mapper = Mappers.getMapper(PatientMapper.class);

    @Autowired
    private PatientRepository repository;

    public Patient createPatient(PatientDto patientDto) {
        Patient patient = mapper.mapToEntity(patientDto);
        patient.setCreationDate(new Date());
        repository.savePatient(patient);
        return patient;
    }

    public Patient getPatientByPhone(String phone){
       return repository.getPatientByPhone(phone);
    }
}
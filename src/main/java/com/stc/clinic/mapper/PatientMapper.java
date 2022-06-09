package com.stc.clinic.mapper;

import com.stc.clinic.dto.PatientDto;
import com.stc.clinic.entity.Patient;
import org.mapstruct.Mapper;

@Mapper
public interface PatientMapper {

    Patient mapToEntity(PatientDto dto);

    PatientDto mapToDto(Patient entity);
}
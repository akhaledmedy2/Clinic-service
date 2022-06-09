package com.stc.clinic.mapper;

import com.stc.clinic.dto.AppointmentDto;
import com.stc.clinic.entity.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface AppointmentMapper {

    AppointmentDto mapToDto(Appointment entity);

    @Mappings({
            @Mapping(target = "patient", source = "dto.patient" , ignore = true)
    })
    Appointment mapToEntity(AppointmentDto dto);
}
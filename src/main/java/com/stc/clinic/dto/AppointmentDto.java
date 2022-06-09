package com.stc.clinic.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {
    private String cancellationReason;
    private String appointmentId;
    private boolean running;
    private Date appointmentDate;
    private PatientDto patient;
}
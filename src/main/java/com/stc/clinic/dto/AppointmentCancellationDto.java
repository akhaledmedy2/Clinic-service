package com.stc.clinic.dto;

import lombok.Data;

@Data
public class AppointmentCancellationDto {
    private long appointmentId;
    private String cancellationReason;
}
package com.stc.clinic.rest;

import com.stc.clinic.dto.*;
import com.stc.clinic.service.AppointmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Appointment", tags = "Appointment", description = "Appointment Endpoints")
@RestController
@RequestMapping(path = "/appointment")
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = "/get/today", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDto>> getTodayAppointments() {
        return new ResponseEntity<>(appointmentService.getTodayAppointments(), HttpStatus.OK);
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDto> createAppointments(@RequestBody AppointmentDto appointmentDto) {
        return new ResponseEntity<>(appointmentService.createAppointment(appointmentDto), HttpStatus.OK);
    }

    @PostMapping(path = "/get/date", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByDate(@RequestBody AppointmentFilterDateDto filterDateDto) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByDate(filterDateDto.getDate()), HttpStatus.OK);
    }

    @PostMapping(path = "/get/patient", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPatientName(@RequestBody AppointmentFilterPatientDto filterPatientDto) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientName(filterPatientDto.getPatientName()), HttpStatus.OK);
    }

    @PostMapping(path = "/get/patient/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentDto>> getAppointmentsByPatientHistory(@RequestBody AppointmentFilterPatientHistoryDto patientHistoryDto) {
        return new ResponseEntity<>(appointmentService.getAppointmentsByPatientPhone(patientHistoryDto.getPatientPhone()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDto> cancelAppointment(@RequestBody AppointmentCancellationDto cancellationDto) {
        return new ResponseEntity<>(appointmentService.cancelAppointment(cancellationDto), HttpStatus.OK);
    }
}
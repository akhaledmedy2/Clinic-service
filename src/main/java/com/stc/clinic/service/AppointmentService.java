package com.stc.clinic.service;

import com.stc.clinic.dto.AppointmentCancellationDto;
import com.stc.clinic.dto.AppointmentDto;
import com.stc.clinic.entity.Appointment;
import com.stc.clinic.entity.Patient;
import com.stc.clinic.mapper.AppointmentMapper;
import com.stc.clinic.repository.AppointmentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AppointmentService {

    private AppointmentMapper mapper = Mappers.getMapper(AppointmentMapper.class);

    private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private PatientService patientService;

    public List<AppointmentDto> getTodayAppointments() {

        Date start = atStartOfDay(new Date());
        Date end = atEndOfDay(new Date());

        List<Appointment> appointmentList = repository.getAppointmentsByDate(start,end);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentDtos.add(mapper.mapToDto(appointment));
        }
        return appointmentDtos;
    }

    public List<AppointmentDto> getAppointmentsByDate(Date appointmentDate) {

        Date start = atStartOfDay(appointmentDate);
        Date end = atEndOfDay(appointmentDate);

        List<Appointment> appointmentList = repository.getAppointmentsByDate(start,end);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentDtos.add(mapper.mapToDto(appointment));
        }
        return appointmentDtos;
    }

    public List<AppointmentDto> getAppointmentsByPatientName(String patientName) {

        List<Appointment> appointmentList = repository.getAppointmentsByPatientName(patientName);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentDtos.add(mapper.mapToDto(appointment));
        }
        return appointmentDtos;
    }

    public List<AppointmentDto> getAppointmentsByPatientPhone(String phone) {

        List<Appointment> appointmentList = repository.getAppointmentsByPatientPhone(phone);
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentList) {
            appointmentDtos.add(mapper.mapToDto(appointment));
        }
        return appointmentDtos;
    }

    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Patient patient = patientService.getPatientByPhone(appointmentDto.getPatient().getPhone());
        if (patient == null) {
            patient = patientService.createPatient(appointmentDto.getPatient());
        }
        Appointment appointment = mapper.mapToEntity(appointmentDto);
        appointment.setCreationDate(new Date());
        appointment.setRunning(true);
        appointment.setAppointmentId(createNewAppointmentId());
        appointment.setPatient(patient);
        repository.saveAppointment(appointment);
        return mapper.mapToDto(appointment);
    }

    public AppointmentDto cancelAppointment(AppointmentCancellationDto cancellationDto) {
       Appointment appointment = repository.getAppointmentsByAppointmentId(cancellationDto.getAppointmentId());
       if(appointment==null){
           throw new EntityNotFoundException();
       }
       appointment.setRunning(false);
       appointment.setCancellationReason(cancellationDto.getCancellationReason());
       repository.saveAppointment(appointment);
       return mapper.mapToDto(appointment);
    }

    private long createNewAppointmentId() {
        return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) ->
                next > prev ? next : prev + 1);
    }

    private Date atStartOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return localDateTimeToDate(startOfDay);
    }

    private Date atEndOfDay(Date date) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return localDateTimeToDate(endOfDay);
    }

    private static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    private static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
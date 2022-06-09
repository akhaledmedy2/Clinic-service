package com.stc.clinic.repository;

import com.stc.clinic.dao.AppointmentDao;
import com.stc.clinic.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class AppointmentRepository {

    @Autowired
    private AppointmentDao dao;

    public List<Appointment> getAppointmentsByDate(Date start , Date end) {
        return dao.findAllByAppointmentDateBetween(start,end);
    }

    public List<Appointment> getAppointmentsByPatientName(String patientName) {
        return dao.findAllByPatient_Name(patientName);
    }

    public List<Appointment> getAppointmentsByPatientPhone(String phone) {
        return dao.findAllByPatient_Phone(phone);
    }

    public Appointment getAppointmentsByAppointmentId(long appointmentId) {
        return dao.findOneByAppointmentId(appointmentId);
    }

    public void saveAppointment(Appointment appointment){
        appointment.setUpdateDate(new Date());
        dao.save(appointment);
    }
}
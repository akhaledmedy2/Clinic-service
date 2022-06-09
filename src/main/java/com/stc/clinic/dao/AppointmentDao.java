package com.stc.clinic.dao;

import com.stc.clinic.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByAppointmentDateBetween(Date start,Date end);

    List<Appointment> findAllByPatient_Name(String name);

    List<Appointment> findAllByPatient_Phone(String phone);

    Appointment findOneByAppointmentId(long appointmentId);
}
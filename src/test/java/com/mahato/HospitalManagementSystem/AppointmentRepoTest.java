package com.mahato.HospitalManagementSystem;

import com.mahato.HospitalManagementSystem.entity.Appointment;
import com.mahato.HospitalManagementSystem.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentRepoTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void createAppointment() {
        Appointment appointment = Appointment.builder()
                .reason("Brain Tumer")
                .appointmentDateAndTime(LocalDateTime.of(2026,2,12,2,0))
                .build();

        Appointment savedAppointment = appointmentService.createAppointment(appointment,3L,4L);

        System.out.println(savedAppointment);
    }

    @Test
    public void reAppointmentDoctor() {
        Appointment appointment = appointmentService.reappointmentDoctor(1L,3L);
        System.out.println(appointment);
    }
}

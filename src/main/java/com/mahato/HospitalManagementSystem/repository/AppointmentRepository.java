package com.mahato.HospitalManagementSystem.repository;

import com.mahato.HospitalManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment,Long> {
}

package com.mahato.HospitalManagementSystem.service;

import com.mahato.HospitalManagementSystem.entity.Appointment;
import com.mahato.HospitalManagementSystem.entity.Doctor;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.repository.AppointmentRepository;
import com.mahato.HospitalManagementSystem.repository.DoctorRepository;
import com.mahato.HospitalManagementSystem.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        if(appointment.getId() != null) throw new IllegalArgumentException("Appointment with provided ID already exist");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointments().add(appointment);
        doctor.getAppointments().add(appointment);

        appointmentRepository.save(appointment);

        return appointment;
    }

    @Transactional
    public Appointment reappointmentDoctor(Long appointmentId,Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.getDoctor().getAppointments().remove(appointment);
        appointment.setDoctor(doctor);
        doctor.getAppointments().add(appointment);


        return appointment;
    }
}

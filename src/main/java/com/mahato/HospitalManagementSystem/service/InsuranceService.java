package com.mahato.HospitalManagementSystem.service;

import com.mahato.HospitalManagementSystem.entity.Insurance;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.repository.InsuranceRepository;
import com.mahato.HospitalManagementSystem.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Patient with provided ID is not found"));

        patient.setInsurance(insurance);
        insurance.setPatient(patient); // To maintain the 2 way mapping

        return patient;
    }

    @Transactional
    public Patient removeInsuranceFromPatient(Long insuranceId) {
        Insurance insurance = insuranceRepository.findById(insuranceId).orElseThrow();
        Patient patient = insurance.getPatient();
        patient.setInsurance(null);
        return patient;
    }
}

package com.mahato.HospitalManagementSystem.service;

import com.mahato.HospitalManagementSystem.dto.BloodGroupTypeResponseEntity;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.repository.PatientRepository;
import com.mahato.HospitalManagementSystem.entity.type.BloodGroupType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    @Transactional
    public Patient getPatientById(Long id) {
        Patient p1 = patientRepository.findById(id).orElseThrow();

        return p1;
    }

    public List<Patient> getPatientByName(String name,String email) {
        return patientRepository.findByNameOrEmail(name,email);
    }

    public List<Patient> getPatientByDOBGreaterThan(LocalDate date) {
        return patientRepository.findByBirthDateGreaterThan(date);
    }

    public List<Patient> getPatientDOBBetween(LocalDate start, LocalDate end) {
        Optional<List<Patient>> patients = patientRepository.findByBirthDateInBetween(start,end);
        return patients.get();
    }

    public List<Patient> getPatientByBloodGroup(BloodGroupType bloodGroup) {
        return patientRepository.findByBloodGroup(bloodGroup);
    }

    public List<Object[]> countPatientsOfBG() {
        return patientRepository.countNumberOfPatientsForDifferentBloodGroup();
    }

    public List<Patient> getAllPatientsByNativeQuery() {
        return patientRepository.getAllPatientsByNativeQuery();
    }

    @Transactional
    public int updatePatientById(String name, Long id) {
        return patientRepository.updatePatientById(name,id);
    }

    public List<BloodGroupTypeResponseEntity> findNumberOfPatientsWithSameBloodGroup() {
        return patientRepository.countNumberOfPatientsFromDifferentBloodGroupWithoutUsingObjectArray();
    }

    public List<Patient> getAllUsingPagination(Pageable pageable) {
        return patientRepository.getAllPatientsByNativeQueryPaginated(pageable).getContent();
    }
}

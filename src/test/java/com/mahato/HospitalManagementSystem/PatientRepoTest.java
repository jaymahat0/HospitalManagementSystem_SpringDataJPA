package com.mahato.HospitalManagementSystem;

import com.mahato.HospitalManagementSystem.dto.BloodGroupTypeResponseEntity;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.repository.PatientRepository;
import com.mahato.HospitalManagementSystem.service.PatientService;
import com.mahato.HospitalManagementSystem.entity.type.BloodGroupType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PatientRepoTest {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService patientService;

    @Test
    public void patientRepositoryTest() {
        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
    }

    @Test
    public void testTransactionMethod() {
        Patient patient = patientService.getPatientById(1L);
        System.out.println(patient);
    }


    //=================================QueryMethod===========================================

    @Test
    public void findByNameOrEmailTest() {
        List<Patient> patients = patientService.getPatientByName("Jaydev Mahato","swastikabhumiraj@gmail.com");
        System.out.println(patients);
    }

    @Test
    public void findByDateOfBirthGreaterThan() {
        List<Patient> patients = patientService.getPatientByDOBGreaterThan(LocalDate.of(2003,12,12));
        System.out.println(patients);
    }

    //================================JPQL==================================================

    @Test
    public void findPatientByDOBInBetween() {
        List<Patient> patients = patientService.getPatientDOBBetween(LocalDate.of(2004,1,1),LocalDate.of(2005,3,15));
        System.out.println(patients);
    }

    @Test
    public void findPatientByBloodGroup() {
        List<Patient> patients = patientService.getPatientByBloodGroup(BloodGroupType.B_POSITIVE);
        System.out.println(patients);
    }

    //Using Group By
    @Test
    public void countPatientsOfSameBloodGroup() {
        List<Object[]> bloodGroupList = patientService.countPatientsOfBG();

        for(Object[] objects : bloodGroupList) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void countPatientsOfSameBloodGroupWithoutUsingObjectArray() {
        List<BloodGroupTypeResponseEntity> bloodGroupTypeResponseEntities = patientService.findNumberOfPatientsWithSameBloodGroup();

        for(BloodGroupTypeResponseEntity bloodGroupTypeResponseEntity : bloodGroupTypeResponseEntities) {
            System.out.println(bloodGroupTypeResponseEntity);
        }
    }

    //========================Native Query========================================================

    @Test
    public void getAll() {
        List<Patient> patients = patientService.getAllPatientsByNativeQuery();
        System.out.println(patients);
    }

    //==========================Update Queries===================================================

    @Test
    public void updatePatientById() {
        int rowUpdated = patientService.updatePatientById("Swastika Raj Dangi",2L);
        System.out.println(rowUpdated);
    }


    //=============================Pagination====================================

    @Test
    public void getPatientUsingPagination() {
        List<Patient> patients = patientService.getAllUsingPagination(PageRequest.of(1,2, Sort.by("name")));
        for(Patient patient : patients) {
            System.out.println(patient);
        }
    }

    //===========================Remove Patient================================
    @Test
    public void deletePatientById() {
        Patient patient = patientService.removePatientById(3L);
        System.out.println(patient);
    }
}

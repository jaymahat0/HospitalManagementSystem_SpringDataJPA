package com.mahato.HospitalManagementSystem;

import com.mahato.HospitalManagementSystem.entity.Insurance;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

@SpringBootTest
public class InsuranceRepoTest {
    @Autowired
    private InsuranceService insuranceService;

    @Test
    public void InsuranceInjectionToPatient() {
        Insurance insurance = Insurance.builder()
                .policyNumber("ICICI3907")
                .provider("ICICI Bank")
                .validUntil(LocalDate.of(2035,3,23))
                .build();

        Patient patient = insuranceService.assignInsuranceToPatient(insurance,2L);
        System.out.println(patient);
    }

    @Test
    public void removeInsuranceOfPatient() {
        Patient patient = insuranceService.removeInsuranceFromPatient(1L);
        System.out.println(patient);
    }
}

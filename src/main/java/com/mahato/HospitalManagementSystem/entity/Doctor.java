package com.mahato.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_doctor_name_and_email",columnNames = {"name","email"}),

        }
)
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false,length = 60)
    private String name;

    @Column(name = "email",nullable = false,length = 60)
    @Email
    private String email;

    @Column(name = "specialization",nullable = false,length = 50)
    private String specialization;

    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();

    @OneToOne(mappedBy = "doctorHead")
    private Department department;

    @ManyToMany(mappedBy = "doctorSet")
    @ToString.Exclude
    private Set<Department> departmentSet = new HashSet<>();



}

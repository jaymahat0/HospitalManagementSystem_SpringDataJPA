package com.mahato.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "department"
)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name",nullable = false,length = 50)
    private String department;

    @OneToOne
    @JoinColumn(name = "doctor_head_id",nullable = false)
    private Doctor doctorHead;

    @ManyToMany
    @JoinTable(
            name = "department_doctor_relation",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private Set<Doctor> doctorSet = new HashSet<>();

}

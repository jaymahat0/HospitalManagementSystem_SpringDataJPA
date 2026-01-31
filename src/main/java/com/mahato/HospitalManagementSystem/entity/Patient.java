package com.mahato.HospitalManagementSystem.entity;

import com.mahato.HospitalManagementSystem.entity.type.BloodGroupType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name="patient",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email",columnNames={"email"}),
                @UniqueConstraint(name = "unique_patient_name_birthdate",columnNames = {"name","birthDate"})

        },
        indexes = {
                @Index(name = "index_patient_birthdate", columnList = "birthDate")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ToString.Exclude
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Email
    @ToString.Exclude
    @Column(name = "email", unique = true)
    private String email;

    @ToString.Exclude
    private String gender;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    @ToString.Exclude // It excludes from ToString method.
    private LocalDateTime createdAt;

    @ToString.Exclude
    @Column(name = "blood_group")
    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}

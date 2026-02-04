package com.mahato.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(
        name = "insurance",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_policy_no", columnNames = {"policy_no"})
        }
)
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "policy_no",nullable = false,length = 20)
    private String policyNumber;

    @Column(name = "provider",nullable = false)
    private String provider;

    @Column(name = "valid_until",nullable = false)
    private LocalDate validUntil;

    @Column(name = "create_at",updatable = false)
    @CreationTimestamp
    private LocalDate createAt;

    @OneToOne(mappedBy = "insurance") //Inverse side
    @ToString.Exclude
    private Patient patient;


}

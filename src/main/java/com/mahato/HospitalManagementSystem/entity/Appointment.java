package com.mahato.HospitalManagementSystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "appointment"
)
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "appointment_date_time")


    private LocalDateTime appointmentDateAndTime;

    @ManyToOne(optional = false) /* optional = false -> *Enforces relationship must exist at JPA level
                                                        *Prevents saving entity without a Patient */
    @JoinColumn(name = "patient_id",nullable = false)   // Owning side
    //@ToString.Exclude
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    //@ToString.Exclude
    private Doctor doctor;


}

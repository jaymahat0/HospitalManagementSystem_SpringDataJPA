package com.mahato.HospitalManagementSystem.dto;


import com.mahato.HospitalManagementSystem.entity.type.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupTypeResponseEntity {
    private BloodGroupType bloodGroup;
    private Long count;
}

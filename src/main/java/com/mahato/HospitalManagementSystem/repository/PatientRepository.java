package com.mahato.HospitalManagementSystem.repository;

import com.mahato.HospitalManagementSystem.dto.BloodGroupTypeResponseEntity;
import com.mahato.HospitalManagementSystem.entity.Patient;
import com.mahato.HospitalManagementSystem.entity.type.BloodGroupType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    //=============================Query Methods==================================
    List<Patient> findByNameOrEmail(String name, String email);

    List<Patient> findByBirthDateGreaterThan(LocalDate date);

    //Check out other Query Methods : https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    //=============================JPQL============================================

    @Query("""
             SELECT patient
             FROM Patient patient
             WHERE patient.birthDate >= ?1
             AND patient.birthDate <= ?2
            """)
    Optional<List<Patient>> findByBirthDateInBetween(@Param("birthDate") LocalDate start, @Param("birthDate") LocalDate end);

    @Query("""
            select p
            from Patient p
            where p.bloodGroup = :bloodGroup
            """)
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup);

    @Query("""
            select p.bloodGroup,count(p)
            from Patient p
            group by p.bloodGroup
            """)
    List<Object[]> countNumberOfPatientsForDifferentBloodGroup();

    /* Projection -> Projection in Spring Data JPA means fetching only selected fields (columns)
        from an entity instead of fetching the complete entity.
     */
    @Query("""
            select new com.mahato.HospitalManagementSystem.dto.BloodGroupTypeResponseEntity(p.bloodGroup,count(p.id))
            from Patient p
            group by p.bloodGroup
            """)
    List<BloodGroupTypeResponseEntity> countNumberOfPatientsFromDifferentBloodGroupWithoutUsingObjectArray();

    //=================================Native Query=================================================

    @Query(value = """
            select *
            from patient
            """, nativeQuery = true)
    List<Patient> getAllPatientsByNativeQuery();

    //==============================Update Query==================================================

    @Transactional
    @Modifying
    @Query("""
            UPDATE Patient p
            SET p.name = :name
            WHERE p.id = :id
            """)
    int updatePatientById(@Param("name") String name, @Param("id") Long id);


    //===============================Pagination====================================================
    /*Pagination -> Pagination is the technique of fetching data from the database in smaller chunks
     (pages) instead of loading all records at once.
     */

    @Query(value = """
            select *
            from patient
            """, nativeQuery = true)
    Page<Patient> getAllPatientsByNativeQueryPaginated(Pageable pageable);

}

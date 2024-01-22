package org.example.repositories;

import org.example.models.MedService;
import org.example.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByMedCenterId(Long medCenterId);


}
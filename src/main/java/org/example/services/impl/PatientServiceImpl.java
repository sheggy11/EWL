package org.example.services.impl;

import lombok.RequiredArgsConstructor;
//import org.example.dto.PatientDto;
import org.example.controllers.PatientProfileController;
import org.example.models.MedCenter;
import org.example.models.Patient;
import org.example.repositories.PatientRepository;
import org.example.services.PatientService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> getAllPatients() {

        return patientRepository.findAll();

    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);

    }

    @Override
    public void addPatient(Patient patient) {
        LocalDateTime currentTime = LocalDateTime.now();
        patient.setCreateDate(currentTime);
        patient.setUpdateDate(currentTime);
        patientRepository.save(patient);
    }

    @Override
    public void updatePatient(Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(updatedPatient.getId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        PatientProfileController.updatePatient(updatedPatient, existingPatient);

        patientRepository.save(existingPatient);
    }

    public MedCenter getMedCenterForPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        return (patient != null) ? patient.getMedCenter() : null;
    }

    @Override
    public List<Patient> getPatientsByMedCenterId(Long medCenterId) {
        return patientRepository.findByMedCenterId(medCenterId);
    }

}
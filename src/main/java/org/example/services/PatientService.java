package org.example.services;

//import org.example.dto.PatientDto;
import org.example.models.MedCenter;
import org.example.models.Patient;
import org.thymeleaf.util.PatternUtils;

import java.util.List;

public interface PatientService {

    List<Patient> getAllPatients();

    Patient getPatientById(Long id);

    void addPatient(Patient patient);

    void updatePatient(Patient updatedPatient);

    MedCenter getMedCenterForPatient(Long patientId);

    List<Patient> getPatientsByMedCenterId(Long medCenterId);
}

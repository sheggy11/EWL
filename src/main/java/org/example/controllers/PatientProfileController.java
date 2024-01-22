package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.models.Patient;
import org.example.services.MedCenterService;
import org.example.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientProfileController {

    private final PatientService patientService;

    private final MedCenterService medCenterService;

    @GetMapping("/{patientId}/profile")
    public String showPatientProfile(@PathVariable Long patientId, Model model) {
        Patient patient = patientService.getPatientById(patientId);
        List<MedCenter> medCenters = medCenterService.getAllMedCenters();
        model.addAttribute("medCenters", medCenters);
        model.addAttribute("patient", patient);
        return "patient_profile";
    }

    @PostMapping("/{patientId}/update")
    public String updatePatientProfile(@PathVariable Long patientId, @ModelAttribute("patient") Patient updatedPatient) {
        Patient patient = patientService.getPatientById(patientId);

        updatePatient(updatedPatient, patient);

        List<MedCenter> medCenters = medCenterService.getAllMedCenters();


        if (updatedPatient.getMedCenter() != null) {
            MedCenter selectedCenter = medCenterService.getMedCenterById(updatedPatient.getMedCenter().getId());
            patient.setMedCenter(selectedCenter);
        }

        updatedPatient.setUpdateDate(LocalDateTime.now());



        patientService.updatePatient(patient);
        return "redirect:/patients/" + patientId + "/profile";
    }

    public static void updatePatient(@ModelAttribute("patient") Patient updatedPatient, Patient patient) {
        if (updatedPatient.getFirstName() != null && !updatedPatient.getFirstName().isEmpty()) {
            patient.setFirstName(updatedPatient.getFirstName());
        }
        if (updatedPatient.getLastName() != null && !updatedPatient.getLastName().isEmpty()) {
            patient.setLastName(updatedPatient.getLastName());
        }

        if (updatedPatient.getPatronymic() != null && !updatedPatient.getPatronymic().isEmpty()) {
            patient.setPatronymic(updatedPatient.getPatronymic());
        }

        if (updatedPatient.getDateOfBirth() != null) {
            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
        }

        if (updatedPatient.getPhoneNumber() != null && !updatedPatient.getPhoneNumber().isEmpty()) {
            patient.setPhoneNumber(updatedPatient.getPhoneNumber());
        }

        if (updatedPatient.getMedCenter() != null) //&& !updatedPatient.getMedCenter().isEmpty())
        {
            patient.setMedCenter(updatedPatient.getMedCenter());
        }
    }

    @PostMapping("/{patientId}/updateMedCenter")
    public String updatePatientMedCenter(@PathVariable Long patientId, @RequestParam("medCenterId") Long medCenterId) {
        Patient patient = patientService.getPatientById(patientId);
        MedCenter selectedMedCenter = medCenterService.getMedCenterById(medCenterId);

        if (selectedMedCenter != null) {
            patient.setMedCenter(selectedMedCenter);
            patientService.updatePatient(patient);
        }


        return "redirect:/patients/" + patientId + "/profile";
    }


}


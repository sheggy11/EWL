package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.models.MedService;
import org.example.models.Patient;
import org.example.services.MedCenterService;
import org.example.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients/addNewPatient")
public class AddPatientController {

    private final PatientService patientService;
    private final MedCenterService medCenterService;

    @GetMapping("")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("medCenters", medCenterService.getAllMedCenters());
        return "addNewPatient";
    }

    @PostMapping("")
    public String addPatient(@ModelAttribute("patient") Patient patient, @RequestParam("medCenterId") Long medCenterId) {
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        patient.setMedCenter(medCenter);
        LocalDateTime currentTime = LocalDateTime.now();
        patient.setCreateDate(currentTime);
        patient.setUpdateDate(currentTime);
        patientService.addPatient(patient);
        return "redirect:/patients";
    }
}

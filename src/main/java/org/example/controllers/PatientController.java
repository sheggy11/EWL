package org.example.controllers;

import lombok.RequiredArgsConstructor;
//import org.example.dto.PatientDto;
import org.example.dto.AccountDto;
import org.example.models.MedCenter;
import org.example.models.Patient;
import org.example.services.MedCenterService;
import org.example.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    private final MedCenterService medCenterService;

    @GetMapping()
    public String showAllPatients(Model model) {

        List<Patient> patients = patientService.getAllPatients();
        List<MedCenter> medCenters = medCenterService.getAllMedCenters();
        model.addAttribute("patients", patients);
        model.addAttribute("medCenters", medCenters);
        model.addAttribute("dateTimeFormatter", new DateTimeFormatterWrapper());
        return "patients";
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }
    public class DateTimeFormatterWrapper {
        public String format(LocalDateTime dateTime) {
            return formatDateTime(dateTime);
        }
    }
}

package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.config.details.AccountUserDetails;
import org.example.dto.PatientDto;
import org.example.models.*;
import org.example.services.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final PatientService patientService;

    private final MedServiceService medServiceService;

    private final UrgencyNoteService urgencyNoteService;

    private final AppointmentStatusService appointmentStatusService;

    private final AccountService accountService;

    @GetMapping()
    public String showAddAppointmentForm(Model model, @AuthenticationPrincipal AccountUserDetails userDetails) {

        Optional<Account> accountByEmail = accountService.getAccountByEmail(userDetails.getUsername());
        if (accountByEmail.isPresent()) {
            Account account = accountByEmail.get();
            Long medCenterId = accountService.getMedCenterIdByAccountId(account.getId());

            List<Patient> patients = patientService.getPatientsByMedCenterId(medCenterId);
            List<MedService> medServices = medServiceService.getMedServicesByMedCenterId(medCenterId);


            model.addAttribute("patients", patients);
            model.addAttribute("appointment", new Appointment());

            // нужно ли
            if (patients.isEmpty()) {
                return "appointment";
            }


            model.addAttribute("medServices", medServices);

            List<UrgencyNote> urgencyNotes = urgencyNoteService.getAllUrgencyNotes();
            model.addAttribute("urgencyNotes", urgencyNotes);

            List<AppointmentStatus> appointmentStatuses = appointmentStatusService.getAllAppointmentStatus();
            model.addAttribute("appointmentStatuses", appointmentStatuses);
            return "appointment";

        }
        return "redirect:/error";     }

        @PostMapping()
        public String addAppointment (@ModelAttribute("appointment") Appointment appointment,
                @RequestParam("urgency_note_id") Long urgencyNoteId,
                @RequestParam("appointment_status_id") Long appointmentStatusId,
                @RequestParam("patient_id") Long patientId,
                @RequestParam("med_service_id") Long medServiceId){
            UrgencyNote urgencyNote = urgencyNoteService.findById(urgencyNoteId);
            appointment.setUrgencyNote(urgencyNote);
            AppointmentStatus appointmentStatus = appointmentStatusService.findById(appointmentStatusId);
            appointment.setAppointmentStatus(appointmentStatus);
            Patient patient = patientService.getPatientById(patientId);
            appointment.setPatient(patient);
            MedService medService = medServiceService.getMedServiceById(medServiceId);
            appointment.setMedService(medService);

            appointmentService.addAppointment(appointment);
            return "redirect:/profile";

        }
    }



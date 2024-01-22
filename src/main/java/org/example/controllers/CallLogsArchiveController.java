package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.Appointment;
import org.example.models.CallLogs;
import org.example.services.AppointmentService;
import org.example.services.CallLogsService;
import org.example.services.CallResultService;
import org.example.services.CallStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/callLogs/archive")
public class CallLogsArchiveController {

    private final AppointmentService appointmentService;

    private final CallResultService callResultService;

    private final CallStatusService callStatusService;

    private final CallLogsService callLogsService;

    @GetMapping()
    public String showCallLogsArchive(Model model) {

        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);

        model.addAttribute("dateTimeFormatter", new CallLogsArchiveController.DateTimeFormatterWrapper());

        List<Long> callLogsCounts = new ArrayList<>();
        for (Appointment appointment : appointments) {
            Long appointmentId = appointment.getId();
            Long callLogsCount = callLogsService.countCallLogsByAppointmentId(appointmentId);
            callLogsCounts.add(callLogsCount);
        }
        model.addAttribute("callLogsCounts", callLogsCounts);

        List<String> lastCallDates = new ArrayList<>();
        for (Appointment appointment : appointments) {
            Long appointmentId = appointment.getId();
            CallLogs lastCall = callLogsService.getLastCallByAppointmentId(appointmentId);
            String lastCallDate = lastCall != null ? formatDateTime(lastCall.getCallDate()) : "Нет записей";
            lastCallDates.add(lastCallDate);
        }
        model.addAttribute("lastCallDates", lastCallDates);

        return "call_logs_archive";
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

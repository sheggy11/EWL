package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.*;
import org.example.services.AppointmentService;
import org.example.services.CallLogsService;
import org.example.services.CallResultService;
import org.example.services.CallStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/callLogs")
public class CallLogsController {

    private final AppointmentService appointmentService;

    private final CallResultService callResultService;

    private final CallStatusService callStatusService;

    private final CallLogsService callLogsService;



    @GetMapping()
    public String showCallLogs(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);

        List<CallResult> callResults = callResultService.getAllCallResults();
        model.addAttribute("callResults", callResults);

        List<CallStatus> callStatuses = callStatusService.getAllCallStatuses();
        model.addAttribute("callStatuses", callStatuses);

        model.addAttribute("dateTimeFormatter", new CallLogsController.DateTimeFormatterWrapper());

        List<Long> callLogsCounts = new ArrayList<>();
        for (Appointment appointment : appointments) {
            Long appointmentId = appointment.getId();
            Long callLogsCount = callLogsService.countCallLogsByAppointmentId(appointmentId);
            callLogsCounts.add(callLogsCount);

            if (callLogsCount == 3) {
                appointmentService.updateAppointmentStatus(appointmentId, 5L);
            }
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

        return "call_logs";
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



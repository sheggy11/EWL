package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.Appointment;
import org.example.models.CallLogs;
import org.example.models.CallResult;
import org.example.models.CallStatus;
import org.example.services.AppointmentService;
import org.example.services.CallLogsService;
import org.example.services.CallResultService;
import org.example.services.CallStatusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/callLogs")
@Controller
public class CallCardController {
    private final AppointmentService appointmentService;

    private final CallResultService callResultService;

    private final CallStatusService callStatusService;

    private final CallLogsService callLogsService;


    @GetMapping("/callCard/{appointmentId}")
    public String showCallCard(@PathVariable Long appointmentId,
                               Model model) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        model.addAttribute("appointment", appointment);


        List<CallLogs> callLogs = callLogsService.getCallLogsByAppointmentId(appointmentId);
        model.addAttribute("callLogs", callLogs);

        List<CallStatus> callStatuses = callStatusService.getAllCallStatuses();
        model.addAttribute("callStatuses", callStatuses);
        List<CallResult> callResults = callResultService.getAllCallResults();
        model.addAttribute("callResults", callResults);
        return "call_card";
    }

    @GetMapping("/callCard/{appointmentId}/add")
    public String showAddCallLogsForm(Model model, @PathVariable String appointmentId) {
        model.addAttribute("callLogs", new CallLogs());
        model.addAttribute("callStatuses", callStatusService.getAllCallStatuses());
        model.addAttribute("callResults", callResultService.getAllCallResults());

        return "call_card";

    }


    @PostMapping("/callCard/{appointmentId}/add")
    public String addCallLogs(@ModelAttribute("callLogs") CallLogs callLogs,
                              @RequestParam("call_status_id") Long callStatusId,
                              @RequestParam(value = "call_result_id", required = false) Long callResultId,
                              @PathVariable Long appointmentId) {

        callLogs.setAppointment(appointmentService.getAppointmentById(appointmentId));

        CallStatus callStatus = callStatusService.findById(callStatusId);
        callLogs.setCallStatus(callStatus);

        if (callStatus.getName().equals("Дозвонились")) {
            CallResult callResult = callResultService.findById(callResultId);
            callLogs.setCallResult(callResult);
        }

        CallResult callResult = callResultService.findById(callResultId);
        callLogs.setCallResult(callResult);

        LocalDateTime currentTime = LocalDateTime.now();
        callLogs.setCallDate(currentTime);

        callLogsService.addCallLog(callLogs);

        if (callResultId != null && callResultId.equals(1L)) {
            appointmentService.updateAppointmentStatus(appointmentId, 3L);
        }




        return "redirect:/callLogs/callCard/" + appointmentId;

    }
}

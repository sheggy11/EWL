package org.example.services;

import org.example.models.CallLogs;

import java.util.List;

public interface CallLogsService {

    List<CallLogs> getAllCallLogs();
    CallLogs findById(Long id);
    void addCallLog(CallLogs callLog);

    CallLogs getCallLogsById(Long id);

    List<CallLogs> getCallLogsByAppointmentId(Long appointmentId);

    Long countCallLogsByAppointmentId(Long appointmentId);

    CallLogs getLastCallByAppointmentId(Long appointmentId);


}

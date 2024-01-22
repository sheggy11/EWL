package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.models.CallLogs;
import org.example.repositories.CallLogsRepository;
import org.example.services.CallLogsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CallLogsServiceImpl implements CallLogsService {

    private final CallLogsRepository callLogsRepository;

    @Override
    public List<CallLogs> getAllCallLogs() {
        return callLogsRepository.findAll();
    }

    @Override
    public CallLogs findById(Long id) {
        return callLogsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AppointmentStatus с id " + id + " не найдена"));
    }

    @Override
    public void addCallLog(CallLogs callLog) {
        callLogsRepository.save(callLog);
    }

    @Override
    public CallLogs getCallLogsById(Long id) {
        return callLogsRepository.findById(id).orElse(null);

    }
    @Override
    public List<CallLogs> getCallLogsByAppointmentId(Long appointmentId) {
        return callLogsRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public Long countCallLogsByAppointmentId(Long appointmentId) {
        return callLogsRepository.countByAppointmentId(appointmentId);
    }

    @Override
    public CallLogs getLastCallByAppointmentId(Long appointmentId) {
        return callLogsRepository.findTopByAppointmentIdOrderByCallDateDesc(appointmentId);
    }

}


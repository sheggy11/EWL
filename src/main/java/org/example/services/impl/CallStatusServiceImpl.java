package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.models.CallStatus;
import org.example.repositories.CallStatusRepository;
import org.example.services.CallStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class CallStatusServiceImpl implements CallStatusService {

    private final CallStatusRepository callStatusRepository;
    @Override
    public List<CallStatus> getAllCallStatuses() {
        return callStatusRepository.findAll();
    }

    @Override
    public CallStatus findById(Long id) {
        return callStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AppointmentStatus с id " + id + " не найдена"));
    }

}

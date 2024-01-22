package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.models.AppointmentStatus;
import org.example.models.CallResult;
import org.example.repositories.AppointmentStatusRepository;
import org.example.repositories.CallResultRepository;
import org.example.services.CallResultService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class CallResultServiceImpl implements CallResultService {

    private final CallResultRepository callResultRepository;

    @Override
    public List<CallResult> getAllCallResults() {
        return callResultRepository.findAll();
    }

    public CallResult findById(Long id) {
        if (id == null) {

            return null;
        } else {
            return callResultRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("CallResult с id " + id + " не найден"));
        }
    }
}


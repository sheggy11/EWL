package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.models.AppointmentStatus;
import org.example.repositories.AppointmentStatusRepository;
import org.example.services.AppointmentStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Log4j2
@Service
public class AppointmentStatusServiceImpl implements AppointmentStatusService {

    private final AppointmentStatusRepository appointmentStatusRepository;

    @Override
    public List<AppointmentStatus> getAllAppointmentStatus() {
        return appointmentStatusRepository.findAll();
    }

    public AppointmentStatus findById(Long id) {
        return appointmentStatusRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AppointmentStatus с id " + id + " не найдена"));
    }
}

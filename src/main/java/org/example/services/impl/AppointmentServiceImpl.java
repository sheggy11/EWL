package org.example.services.impl;

import lombok.RequiredArgsConstructor;
//import org.example.dto.AppointmentDto;
import org.example.models.Appointment;
import org.example.models.AppointmentStatus;
import org.example.repositories.AppointmentRepository;
import org.example.services.AppointmentService;
import org.example.services.AppointmentStatusService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.example.dto.AccountDto.from;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {


    private final AppointmentRepository appointmentRepository;

    private final AppointmentStatusService appointmentStatusService;


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public void addAppointment(Appointment appointment) {
        LocalDateTime currentTime = LocalDateTime.now();
        appointment.setAppointment_create_date(currentTime);
        appointment.setAppointment_update_date(currentTime);
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);

    }

    @Override
    public void updateAppointmentStatus(Long appointmentId, Long newStatusId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NoSuchElementException("Appointment с id " + appointmentId + " не найден"));

        // Устанавливаем новый статус
        AppointmentStatus newStatus = appointmentStatusService.findById(newStatusId);
        appointment.setAppointmentStatus(newStatus);

        appointmentRepository.save(appointment);
    }


}

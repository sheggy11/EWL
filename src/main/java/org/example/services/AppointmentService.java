package org.example.services;

//import org.example.dto.AppointmentDto;
import org.example.models.Appointment;
import org.example.models.MedCenter;
import org.example.models.MedService;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointments();

    void addAppointment(Appointment appointment);

    List<Appointment> getAppointmentsByPatientId(Long patientId);

    Appointment getAppointmentById(Long id);

    void updateAppointmentStatus(Long appointmentId, Long newStatusId);

}

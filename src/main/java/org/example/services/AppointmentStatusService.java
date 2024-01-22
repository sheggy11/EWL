package org.example.services;

import org.example.models.AppointmentStatus;
import org.example.models.UrgencyNote;

import java.util.List;

public interface AppointmentStatusService {

    List<AppointmentStatus> getAllAppointmentStatus();

    AppointmentStatus findById(Long id);
}

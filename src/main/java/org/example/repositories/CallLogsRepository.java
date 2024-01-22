package org.example.repositories;

import org.example.models.CallLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CallLogsRepository extends JpaRepository<CallLogs, Long> {
    List<CallLogs> findByAppointmentId(Long appointmentId);

    @Query("SELECT COUNT(cl) FROM CallLogs cl WHERE cl.appointment.id = :appointmentId")
    Long countByAppointmentId(@Param("appointmentId") Long appointmentId);

    CallLogs findTopByAppointmentIdOrderByCallDateDesc(Long appointmentId);


}


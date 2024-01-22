package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "appointment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate appointment_date;

    @Column(name = "appointment_note")
    private String appointment_note;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @Column(name = "appointment_create_date")
    private LocalDateTime appointment_create_date;

    @Column(name = "appointment_update_date")
    private LocalDateTime appointment_update_date;

    @ManyToOne
    @JoinColumn(name = "medService_id")
    private MedService medService;

    @ManyToOne
    @JoinColumn(name = "urgencyNote_id")
    private UrgencyNote urgencyNote;

    @ManyToOne
    @JoinColumn(name = "appointmentStatus_id")
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "appointment")
    private List<CallLogs> callLogs;

}

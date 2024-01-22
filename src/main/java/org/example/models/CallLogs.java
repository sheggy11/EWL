package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "call_logs")
public class CallLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "call_date")
    private LocalDateTime callDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "call_status_id")
    private CallStatus callStatus;

    @ManyToOne
    @JoinColumn(name = "call_result_id", nullable = true)
    private CallResult callResult;

}

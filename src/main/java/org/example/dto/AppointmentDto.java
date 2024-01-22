package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.example.models.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String diagnosis;

    private UrgencyNote urgencyNote;

    private LocalDate deadline;

    private MedService medService;

    public static AppointmentDto from(Appointment appointment) {
        return AppointmentDto.builder()
                .id(appointment.getId())
                .diagnosis(appointment.getDiagnosis())
                .deadline(appointment.getDeadline())
                .urgencyNote(appointment.getUrgencyNote())
                .medService(appointment.getMedService())
                .build();
    }

    public static List<AppointmentDto> from(List<Appointment> appointments) {
        return appointments.stream().map(AppointmentDto::from).collect(Collectors.toList());
    }

}

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
public class PatientDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String patronymic;

    @NotBlank
    @Size(min = 11, max = 30)
    private String phoneNumber;

    @NotBlank
    @Size(min = 11, max = 30)
    private LocalDate dateOfBirth;;

    public static PatientDto from(Patient patient) {
        return PatientDto.builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .patronymic(patient.getPatronymic())
                .phoneNumber(patient.getPhoneNumber())
                .dateOfBirth(patient.getDateOfBirth())
                .build();
    }

    public static List<PatientDto> from(List<Patient> patients) {
        return patients.stream().map(PatientDto::from).collect(Collectors.toList());
    }
}

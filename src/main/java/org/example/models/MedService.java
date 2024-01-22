package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MedService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "med_center_id")
    private MedCenter medCenter;

    @OneToMany(mappedBy = "medService", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments;

}


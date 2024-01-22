package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "med_centers")
public class MedCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "medCenter", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(mappedBy = "medCenter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<MedService> medServices;

    @OneToMany(mappedBy = "medCenter")
    private List<Patient> patients;


}
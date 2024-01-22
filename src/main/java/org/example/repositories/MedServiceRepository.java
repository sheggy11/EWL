package org.example.repositories;

import org.example.models.MedCenter;
import org.example.models.MedService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedServiceRepository extends JpaRepository<MedService, Long> {

    List<MedService> findByMedCenterId(Long medCenterId);

    List<MedService> findByMedCenter(MedCenter medCenter);


}

package org.example.services;

import org.example.models.MedCenter;
import org.example.models.MedService;

import java.util.List;

public interface MedServiceService {
    MedService getMedServiceById(Long id);

    List<MedService> getAllMedServices();

    List<MedService> getMedServicesByMedCenterId(Long medCenterId);

    List<MedService> getMedServicesByMedCenter(MedCenter medCenter);
    void addMedService(MedService medService);

    void updateMedService(MedService medService);

    void deleteMedService(Long id);


    List<MedService> getMedServicesForCenter(MedCenter medCenter);
}


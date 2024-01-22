package org.example.services.impl;


import org.example.models.MedCenter;
import org.example.models.MedService;
import org.example.repositories.MedServiceRepository;
import org.example.services.MedServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedServiceServiceImpl implements MedServiceService {

    private final MedServiceRepository medServiceRepository;

    @Autowired
    public MedServiceServiceImpl(MedServiceRepository medServiceRepository) {
        this.medServiceRepository = medServiceRepository;
    }

    @Override
    public MedService getMedServiceById(Long id) {
        return medServiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<MedService> getAllMedServices() {
        return medServiceRepository.findAll();
    }

    @Override
    public List<MedService> getMedServicesByMedCenterId(Long medCenterId) {
        return medServiceRepository.findByMedCenterId(medCenterId);
    }
    @Override
    public List<MedService> getMedServicesByMedCenter(MedCenter medCenter) {
        return medServiceRepository.findByMedCenter(medCenter);
    }

    @Override
    @Transactional
    public void addMedService(MedService medService) {
        medServiceRepository.save(medService);
    }

    @Override
    @Transactional
    public void updateMedService(MedService medService) {
        MedService existingService = medServiceRepository.findById(medService.getId()).orElse(null);
        if (existingService != null) {
            existingService.setName(medService.getName());
            existingService.setMedCenter(medService.getMedCenter());
            existingService.setStatus(medService.getStatus());
            medServiceRepository.save(existingService);
        }
    }

    @Override
    @Transactional
    public void deleteMedService(Long id) {
        medServiceRepository.deleteById(id);
    }

    public List<MedService> getMedServicesForCenter(MedCenter medCenter) {
        return medServiceRepository.findByMedCenter(medCenter);
    }
}



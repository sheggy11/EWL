package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.repositories.MedCenterRepository;
import org.example.services.MedCenterService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedCenterServiceImpl implements MedCenterService {

    private final MedCenterRepository medCenterRepository;

    @Override
    public List<MedCenter> getAllMedCenters() {
        return medCenterRepository.findAll();
    }

    @Override
    public void addMedCenter(MedCenter medCenter) {
        medCenterRepository.save(medCenter);
    }

    @Override
    public MedCenter getMedCenterById(Long id) {
        return medCenterRepository.findById(id).orElse(null);
    }

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public MedCenter getMedCenterWithPatients(Long id) {
        MedCenter medCenter = entityManager.find(MedCenter.class, id);
        Hibernate.initialize(medCenter.getPatients());
        return medCenter;
    }
}
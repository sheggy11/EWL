package org.example.services;

import org.example.models.MedCenter;
import java.util.List;

public interface MedCenterService {
    List<MedCenter> getAllMedCenters();

    void addMedCenter(MedCenter medCenter);

    MedCenter getMedCenterById(Long id);

    MedCenter getMedCenterWithPatients(Long id);
}

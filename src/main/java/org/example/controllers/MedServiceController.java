package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.models.MedService;
import org.example.services.MedCenterService;
import org.example.services.MedServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@Controller
@RequestMapping("/medCentersSettings/medServices")
public class MedServiceController {

    private final MedCenterService medCenterService;

    private final MedServiceService medServiceService;


    @GetMapping("/{medCenterId}")
    public String showMedServices(
            @PathVariable Long medCenterId,
            @RequestParam(value = "addMedService", required = false) boolean addMedService,
            Model model
    ) {
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        List<MedService> medServices = medCenter.getMedServices();
        model.addAttribute("medCenter", medCenter);
        model.addAttribute("services", medServices);
        model.addAttribute("addMedServiceFormEnabled", addMedService);
        return "medServices";
    }

    @PostMapping("/{medCenterId}/add")
    public String addMedServiceToMedCenter(
            @PathVariable Long medCenterId,
            @ModelAttribute("newMedService") MedService newMedService
    ) {
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        newMedService.setMedCenter(medCenter);
        medServiceService.addMedService(newMedService);
        return "redirect:/medCentersSettings/medServices/" + medCenterId;
    }

    @GetMapping("/{medCenterId}/add")
    public String showAddMedServiceForm(@PathVariable Long medCenterId, Model model) {
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        MedService newMedService = new MedService();
        model.addAttribute("medCenter", medCenter);
        model.addAttribute("newMedService", newMedService);
        return "medServices";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedServiceForm(@PathVariable Long id, Model model) {
        MedService medService = medServiceService.getMedServiceById(id);
        model.addAttribute("medService", medService);
        model.addAttribute("pageType", "edit");
        return "medServices";
    }

    @PostMapping("/edit/{medCenterId}/{id}")
    public String editMedServiceStatus(@PathVariable Long id, @PathVariable Long medCenterId, @ModelAttribute MedService updatedMedService) {
        MedService medService = medServiceService.getMedServiceById(id);
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        medService.setStatus(updatedMedService.getStatus());
        medServiceService.updateMedService(medService);

        return "redirect:/medCentersSettings/medServices/" + medCenter.getId();
    }

}


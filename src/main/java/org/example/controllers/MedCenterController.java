package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.models.MedService;
import org.example.services.AccountService;
import org.example.services.MedCenterService;
import org.example.services.MedServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/medCentersSettings")
public class MedCenterController {

    private final MedCenterService medCenterService;
    private final MedServiceService medServiceService;
    private final AccountService accountsService;

    @GetMapping()
    public String showMedCentersSettingsPage(Model model) {
        List<MedCenter> medCenters = medCenterService.getAllMedCenters();

                model.addAttribute("medCenters", medCenters);

                return "medCentersSettings";

    }

    @GetMapping("/add")
    public String showAddMedCenterForm(Model model) {
        model.addAttribute("medCenter", new MedCenter());
        return "medCentersSettings";
    }

    @PostMapping("/add")
    public String addMedCenter(@ModelAttribute MedCenter medCenter) {
        medCenterService.addMedCenter(medCenter);
        return "redirect:/medCentersSettings/";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedCenterForm(@PathVariable Long id, Model model) {
        MedCenter medCenter = medCenterService.getMedCenterById(id);
        model.addAttribute("medCenter", medCenter);
        model.addAttribute("pageType", "edit");
        return "medCentersSettings";
    }

    @PostMapping("/edit/{id}")
    public String editMedCenter(@PathVariable Long id, @ModelAttribute MedCenter updatedMedCenter) {
        MedCenter medCenter = medCenterService.getMedCenterById(id);
        medCenter.setStatus(updatedMedCenter.getStatus());
        medCenterService.addMedCenter(medCenter);
        return "redirect:/medCentersSettings";
    }


    @GetMapping("/medCentersSettings/services/{medCenterId}")
    public String showMedCenterServices(
            @PathVariable Long medCenterId,
            @RequestParam(value = "addMedService", defaultValue = "false") boolean addMedService,
            Model model
    ) {
        MedCenter medCenter = medCenterService.getMedCenterById(medCenterId);
        List<MedService> medServices = medServiceService.getMedServicesByMedCenterId(medCenterId);
        model.addAttribute("medCenter", medCenter);
        model.addAttribute("medServices", medServices);
        model.addAttribute("addMedServiceFormEnabled", addMedService);
        return "medServices";
    }

}

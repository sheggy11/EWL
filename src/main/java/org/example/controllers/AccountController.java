package org.example.controllers;


import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.models.MedCenter;
import org.example.services.AccountService;
import org.example.services.MedCenterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final MedCenterService medCenterService;



    @GetMapping()
    public String showAllAccounts(Model model){
        List<AccountDto> accounts = accountService.getAllAccounts();
        List<MedCenter> medCenters = medCenterService.getAllMedCenters();
        model.addAttribute("accounts", accounts);
        model.addAttribute("medCenters", medCenters);
        return "accounts";
    }

}

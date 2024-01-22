package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.repositories.MedCenterRepository;
import org.example.services.MedCenterService;
import org.example.services.MedServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.example.dto.AccountDto;

import org.example.models.Account;
import org.example.config.details.AccountUserDetails;
import org.example.services.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProfileController {


    private final AccountService accountService;
    private final MedCenterService medCenterService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        Optional<Account> accountByEmail = accountService.getAccountByEmail(userDetails.getUsername());
        if (accountByEmail.isPresent()) {
            Account account = accountByEmail.get();
            model.addAttribute("account", account);
        }
        return "profile";
    }

    @GetMapping("/profile/update")
    public String getEditPage(Model model) {

        model.addAttribute("accountDto", new AccountDto());
        return "changes";
    }

//не работает
    @PostMapping("/profile/update")
    public String update(@Valid AccountDto accountDto, BindingResult result, @AuthenticationPrincipal AccountUserDetails userDetails, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountDto", accountDto);
            return "changes";
        }
        String email = userDetails.getUsername();
        accountService.update(accountDto, email);
        return "redirect:/profile";
    }

    @GetMapping("/profile/updateMedCenter")
    public String selectMedCenter(Model model) {
        List<MedCenter> medCenters = medCenterService.getAllMedCenters();
        model.addAttribute("accountDto", new AccountDto());
        return "changes";
    }

}
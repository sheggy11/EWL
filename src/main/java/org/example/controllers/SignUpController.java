package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.models.MedCenter;
import org.example.services.MedCenterService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.example.dto.SignUpForm;
import org.example.services.AccountService;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SignUpController {

    private final AccountService accountService;

    private final MedCenterService medCenterService;

    @GetMapping("/signUp")
    public String getSignUpPage(Authentication authentication, Model model) {
        if (authentication != null) {
            return "redirect:/";
        }
        List<MedCenter> medCenterList = medCenterService.getAllMedCenters();
        model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute("medCenterList", medCenterList);
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model, Long medId) {
//        if (result.hasErrors()) {
//            model.addAttribute("signUpForm", form);
//            return "sign_up";
//        }
        accountService.signUp(form);
        return "redirect:/signIn";
    }
}
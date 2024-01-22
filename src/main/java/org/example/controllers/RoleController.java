package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.AccountDto;
import org.example.models.Role;
import org.example.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.services.AccountService;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/rolesSettings")
public class RoleController {

    private final AccountService accountsService;
    private final RoleService roleService;




    @GetMapping()
    public String showAccountsSettingsPage(Model model) {
        List<AccountDto> accounts = accountsService.getAllAccounts();
        List<Role> roles = roleService.getAllRoles();

        model.addAttribute("accounts", accounts);
        model.addAttribute("roles", roles);
        return "rolesSettings";
    }

    @PostMapping("/add")
    public String assignRole(@RequestParam Long userId, @RequestParam Long roleId) throws RoleNotFoundException {
        accountsService.addRole(userId, roleId);
        return "redirect:/rolesSettings";
    }

    @Transactional
    @PostMapping("/remove")
    public String removeRole(@RequestParam Long userId, @RequestParam Long roleId) throws RoleNotFoundException {
        accountsService.removeUserRole(userId, roleId);
        return "redirect:/rolesSettings";
    }


}
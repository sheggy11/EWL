package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.models.MedCenter;
import org.example.repositories.MedCenterRepository;
import org.example.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.dto.AccountDto;
import org.example.dto.SignUpForm;
import org.example.helper.exceptions.AccountNotExistsException;
import org.example.models.Account;
import org.example.models.Role;
import org.example.repositories.AccountRepository;
import org.example.services.AccountService;
import javax.management.relation.RoleNotFoundException;
import java.util.*;
import org.example.models.MedCenter;

import static org.example.dto.AccountDto.from;

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final MedCenterRepository medCenterRepository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public void signUp(SignUpForm signUpForm) {
        MedCenter medCenter = medCenterRepository.getById(signUpForm.getMedCenter());
        Account newUser = Account.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .medCenter(medCenter)
                .email(signUpForm.getEmail().toLowerCase(Locale.ROOT))
                .patronymic(signUpForm.getPatronymic())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .state(Account.State.CONFIRMED)
                .build();

        Role userRole = roleRepository.findByRoleName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        newUser.setRoles(roles);

        accountRepository.save(newUser);
    }

    @Transactional
    @Override
    public void removeUserRole(Long userId, Long roleId) throws RoleNotFoundException {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AccountNotExistsException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

        if (account.getRoles().contains(role)) {
            account.getRoles().remove(role);
            accountRepository.save(account);
        }
    }


    @Override
    public void addRole(Long userId, Long roleId) throws RoleNotFoundException {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new AccountNotExistsException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RoleNotFoundException("Role not found"));

        if (account != null && role != null) {
            account.getRoles().add(role);
            accountRepository.save(account);
        }
}


    @Override
    public List<AccountDto> getAllAccounts() {
        return from(accountRepository.findAllByState(Account.State.CONFIRMED));
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotExistsException("User not found"));
        account.setState(Account.State.DELETED);
        accountRepository.save(account);
    }


    @Override
    public void update(AccountDto accountDto, String email) {
        accountRepository.updateUser(
                accountDto.getFirstName(),
                accountDto.getLastName(),
                passwordEncoder.encode(accountDto.getPassword()),
                email,
                accountDto.getMedCenter().getName()
        );
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }


    @Override
    public Long getMedCenterIdByAccountId(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotExistsException("User not found"));

        MedCenter medCenter = account.getMedCenter();
        return (medCenter != null) ? medCenter.getId() : null;
    }
}
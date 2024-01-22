package org.example.services;

import org.example.dto.AccountDto;
import org.example.dto.SignUpForm;
import org.example.models.Account;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    void signUp(SignUpForm form);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long accountId);
    void update(AccountDto accountDto, String email);
    Optional<Account> getAccountByEmail(String email);

    void removeUserRole(Long userId, Long roleId) throws RoleNotFoundException;

    void addRole (Long userId, Long roleId) throws RoleNotFoundException;

    Long getMedCenterIdByAccountId(Long accountId);
}
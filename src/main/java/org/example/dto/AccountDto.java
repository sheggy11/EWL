package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.example.helper.Validator;
import org.example.models.Account;
import org.example.models.MedCenter;
import org.example.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

        private Long id;

        @NotBlank
        @Size(min = 2, max = 20)
        private String firstName;

        @NotBlank
        @Size(min = 2, max = 20)
        private String lastName;

        @NotBlank
        @Size(min = 2, max = 20)
        private String patronymic;

        @NotBlank
        @Size(min = 2, max = 255)
        private MedCenter medCenter;

        @Validator
        private String password;

        private String email;

        private List<Role> roles;

        public static AccountDto from(Account account){
                return AccountDto.builder()
                        .id(account.getId())
                        .firstName(account.getFirstName())
                        .lastName(account.getLastName())
                        .patronymic(account.getPatronymic())
                        .password(account.getPassword())
                        .email(account.getEmail())
                        .medCenter(account.getMedCenter())
                        .build();
        }
        public static List<AccountDto> from(List<Account> accounts) {
                return accounts.stream().map(AccountDto::from).collect(Collectors.toList());
        }

}



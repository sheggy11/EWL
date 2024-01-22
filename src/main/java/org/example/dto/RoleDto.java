package org.example.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Account;
import org.example.models.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {

    private Long id;

    private String roleName;

    private String name;

    private List<String> accounts;

    public static RoleDto from(Role roles) {
        RoleDto roleDto = RoleDto.builder()
                .id(roles.getId())
                .roleName(roles.getRoleName())
                .name(roles.getName())
                .build();

        return roleDto;
    }

    public static List<RoleDto> from(List<Role> roles) {
        return roles.stream().map(RoleDto::from).collect(Collectors.toList());
    }
}
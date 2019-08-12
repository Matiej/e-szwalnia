package com.eszwalnia.timesh.authUser.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthUserRoleDto {

    private Long id;
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthUserDto> authUserDtoSet = new HashSet<>();

    public AuthUserRoleDto(String name) {
        this.name = name;
    }
}

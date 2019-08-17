package com.eszwalnia.timesh.authUser.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUserRoleDto {

    private Long id;
    private String name;
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Set<AuthUserDto> authUserDtoSet = new HashSet<>();

    public AuthUserRoleDto(String name) {
        this.name = name;
    }
}

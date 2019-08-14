package com.eszwalnia.timesh.authUser.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.eszwalnia.timesh.employee.domain.dto.EmployeeDto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class AuthUserDto {

    //todo make validation on fields -> form email own validation.
    //todo zrobic date aby byla UTF
    // zrobic createUserDto bez ID,
    private Long id;
    private String email;
    private String password;
    private String matchPassword;
    private LocalDateTime createdDate;
    private LocalDateTime firstLogon;
    private LocalDateTime lastLogon;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthUserRoleDto> authUserRoleSet = new HashSet<>();
    private EmployeeDto employee;

    public AuthUserDto(String email, String password, String matchPassword) {
        this.email = email;
        this.password = password;
        this.matchPassword = matchPassword;
    }
}

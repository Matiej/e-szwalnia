package com.eszwalnia.timesh.authUser.domain.dto;

import com.eszwalnia.timesh.employee.domain.dto.CreateEmployeeDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateAuthUserDto {

    private String email;
    private String password;
    private String matchPassword;
    private LocalDateTime createdDate;
    private String[] authRoles;
    private CreateEmployeeDto createEmployeeDto;

}

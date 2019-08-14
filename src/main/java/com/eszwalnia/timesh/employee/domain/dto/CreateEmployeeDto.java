package com.eszwalnia.timesh.employee.domain.dto;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateEmployeeDto {

    private String name;
    private String lastName;
    private String email;
    private String phoneNo;
    private LocalDate workStartDate;
//    private AuthUserDto authUserDto;
}

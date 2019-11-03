package com.eszwalnia.timesh.authUser;

import com.eszwalnia.timesh.authUser.domain.AuthUser;
import com.eszwalnia.timesh.authUser.domain.AuthUserService;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.employee.domain.dto.CreateEmployeeDto;
import com.eszwalnia.timesh.employee.domain.dto.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TshRunns  implements CommandLineRunner {

    private final AuthUserService authUserService;


    @Override
    public void run(String... args) throws Exception {

        CreateEmployeeDto ce = new CreateEmployeeDto();
        ce.setName("Stefan");
        ce.setLastName("Batory");
        ce.setPhoneNo("999");
        ce.setWorkStartDate(LocalDate.now());
        ce.setEmail("workers@email.com");


        CreateAuthUserDto createAuthUserDto = new CreateAuthUserDto();
        createAuthUserDto.setEmail("myEmail@gmail.com");
        createAuthUserDto.setMatchPassword("password");
        createAuthUserDto.setPassword("password");
        createAuthUserDto.setAuthRoles(new String[]{"ADMIN"});
        createAuthUserDto.setCreateEmployeeDto(ce);
        authUserService.createAuthUser(createAuthUserDto);
    }
}

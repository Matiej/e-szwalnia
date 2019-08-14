package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.employee.domain.Employee;
import com.eszwalnia.timesh.employee.domain.dto.CreateEmployeeDto;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class CreateAuthUserDtoConventer {

    private final AuthUserRoleRepository authUserRoleRepository;

    public AuthUser convertToAuthUser(CreateAuthUserDto createAuthUserDto) {
        AuthUser authUser = new AuthUser();
        authUser.setEmail(createAuthUserDto.getEmail());
        authUser.setPassword(createAuthUserDto.getPassword());
        authUser.setMatchPassword(createAuthUserDto.getMatchPassword());
        authUser.setCreatedDate(createAuthUserDto.getCreatedDate());
        authUser.setAuthUserRoleSet(authUserRoleSet(createAuthUserDto.getAuthRoles()));
        Employee employee = employee(createAuthUserDto.getCreateEmployeeDto());
        authUser.setEmployee(employee);
        employee.setAuthUser(authUser);

        return authUser;
    }

    private Set<AuthUserRole> authUserRoleSet(String[] roles) {
        return Arrays.stream(roles).map(authUserRoleRepository::findByName).collect(Collectors.toSet());
    }

    private Employee employee(CreateEmployeeDto createEmployeeDto) {
        Employee employee = new Employee();
        employee.setName(createEmployeeDto.getName());
        employee.setLastName(createEmployeeDto.getLastName());
        employee.setEmail(createEmployeeDto.getEmail());
        employee.setPhoneNo(createEmployeeDto.getPhoneNo());
        employee.setWorkStartDate(createEmployeeDto.getWorkStartDate());

        return employee;
    }
}

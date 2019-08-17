package com.eszwalnia.timesh.employee.domain;

import com.eszwalnia.timesh.authUser.domain.AuthUserMapper;
import com.eszwalnia.timesh.authUser.domain.AuthUserRoleMapper;
import com.eszwalnia.timesh.employee.domain.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {
                AuthUserMapper.class
        })
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);
}

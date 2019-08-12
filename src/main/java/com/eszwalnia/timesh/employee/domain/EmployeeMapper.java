package com.eszwalnia.timesh.employee.domain;

import com.eszwalnia.timesh.employee.domain.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeDto employeeDto);

    EmployeeDto toDto(Employee employee);
}

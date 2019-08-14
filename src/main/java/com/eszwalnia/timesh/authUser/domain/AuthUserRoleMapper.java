package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserRoleDto;
import com.eszwalnia.timesh.employee.domain.EmployeeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS,
        uses = {
                AuthUserMapper.class, EmployeeMapper.class
        }
)
public interface AuthUserRoleMapper {

    Set<AuthUserRole> authUserRoleTDtoToEntity(Set<AuthUserRoleDto> authUserRoleDtoSet);

    Set<AuthUserRoleDto> authUserRoleToDto(Set<AuthUserRole> authUserRoleSet);
}

package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.configuration.CycleAvoidingMappingContext;
import com.eszwalnia.timesh.employee.domain.EmployeeMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy =  NullValueCheckStrategy.ALWAYS,
//        disableSubMappingMethodsGeneration=true,
        uses = {
                EmployeeMapper.class, AuthUserRoleMapper.class
        })

public interface AuthUserMapper {

    AuthUserMapper INSTANCE = Mappers.getMapper(AuthUserMapper.class);

    AuthUser authUserDtoToEntity(AuthUserDto authUserDto, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    AuthUserDto authUserToDto(AuthUser authUser,  @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

//    Set<AuthUserRoleDto> toAuthUserRoleDtoSet(Set<AuthUserRole> authUserRoleSet, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    Set<AuthUserDto> authUserDtoSet(Set<AuthUser> authUserSet);
}

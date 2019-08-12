package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserMapper {

    AuthUserMapper INSTANCE = Mappers.getMapper(AuthUserMapper.class);

    AuthUser authUserDtoToEntity(AuthUserDto authUserDto);

    AuthUserDto authUserToDto(AuthUser authUser);

    AuthUserRole authUserRoleTDtoToEntity(AuthUserRoleDto authUserRoleDto);

    AuthUserRoleDto authUserRoleToDto(AuthUserRole authUserRole);
}

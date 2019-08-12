package com.eszwalnia.timesh.authUser;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;

import java.util.List;

public interface AuthUserService {

    AuthUserDto createAuthUser(AuthUserDto authUserDto);

    List<AuthUserDto> findAll();
}

package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;

import java.util.List;

public interface AuthUserService {

    AuthUserDto createAuthUser(CreateAuthUserDto CreateAuthUserDto);

    List<AuthUserDto> findAll();
}

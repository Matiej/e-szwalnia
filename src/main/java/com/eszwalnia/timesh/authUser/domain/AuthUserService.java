package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserNotFoundException;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.exceptionHandler.ExistEmailException;

import java.util.List;

public interface AuthUserService {

    AuthUserDto createAuthUser(CreateAuthUserDto CreateAuthUserDto);

    List<AuthUserDto> findAll();

    AuthUserDto updateAuthUser(AuthUserDto authUserDto) throws ExistEmailException, AuthUserNotFoundException;

    void deleteAuthUser(Long id);
}

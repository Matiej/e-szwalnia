package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserNotFoundException;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.exceptionHandler.ExistEmailException;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthUserService extends UserDetailsService {

    AuthUserDto createAuthUser(CreateAuthUserDto CreateAuthUserDto) throws HibernateException, ExistEmailException;

    List<AuthUserDto> findAll() throws HibernateException;

    AuthUserDto updateAuthUser(AuthUserDto authUserDto) throws ExistEmailException, HibernateException,
            AuthUserNotFoundException;

    boolean deleteAuthUser(Long id);
}

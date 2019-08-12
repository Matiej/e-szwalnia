package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthUserConfiguration {

    private final AuthUserRepository repository;

    @Bean
    AuthUserService authUserService() {
        AuthUserMapper authUserMapper = AuthUserMapper.INSTANCE;
        return new AuthUserServiceImpl(authUserMapper, repository);
    }
}

package com.eszwalnia.timesh.authUser.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class AuthUserConfiguration {

    private final AuthUserRepository repository;
    private final AuthUserRoleRepository authUserRoleRepository;

    @Bean
    AuthUserService authUserService() {
        AuthUserMapper authUserMapper = AuthUserMapper.INSTANCE;

        return new AuthUserServiceImpl(authUserMapper, repository, new CreateAuthUserDtoConventer(authUserRoleRepository));
    }
}

package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserService;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;

    @Override
    public AuthUserDto createAuthUser(AuthUserDto authUserDto) {
        AuthUser authUser = authUserRepository.save(authUserMapper.authUserDtoToEntity(authUserDto));
        return authUserMapper.authUserToDto(authUser);
    }

    @Override
    public List<AuthUserDto> findAll() {

        return authUserRepository.findAll().stream()
                .map(authUserMapper::authUserToDto)
                .collect(Collectors.toList());
    }
}

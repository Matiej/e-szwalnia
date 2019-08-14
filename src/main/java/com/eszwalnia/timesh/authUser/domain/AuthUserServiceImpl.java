package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.configuration.CycleAvoidingMappingContext;
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
    private final CreateAuthUserDtoConventer createAuthUserDtoConventer;

    @Override
    public AuthUserDto createAuthUser(CreateAuthUserDto createAuthUserDto) {
        AuthUser createdAuthUser = createAuthUserDtoConventer.convertToAuthUser(createAuthUserDto);
        authUserRepository.save(createdAuthUser);
        return authUserMapper.authUserToDto(createdAuthUser,  new CycleAvoidingMappingContext());
    }

    @Override
    public List<AuthUserDto> findAll() {
        return authUserRepository.findAll().stream()
                .map(a-> authUserMapper.authUserToDto(a,  new CycleAvoidingMappingContext()))
                .collect(Collectors.toList());
    }
}

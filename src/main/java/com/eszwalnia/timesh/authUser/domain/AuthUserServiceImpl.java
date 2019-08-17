package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserNotFoundException;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.configuration.CycleAvoidingMappingContext;
import com.eszwalnia.timesh.exceptionHandler.ExistEmailException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;
    private final CreateAuthUserDtoConventer createAuthUserDtoConventer;

    @Override
    @Transactional
    public AuthUserDto createAuthUser(CreateAuthUserDto createAuthUserDto) throws HibernateException, ExistEmailException{
        AuthUser createdAuthUser = createAuthUserDtoConventer.convertToAuthUser(createAuthUserDto);
        createdAuthUser.setCreatedDate(LocalDateTime.now().withNano(0));
        try {
            if (isUserEmailExist(0L, createAuthUserDto.getEmail())) {
                throw new ExistEmailException("There is an account with that email address: " + createAuthUserDto.getEmail() +
                        " Can't CREATE user!");
            }
            authUserRepository.save(createdAuthUser);
            return authUserMapper.authUserToDto(createdAuthUser, new CycleAvoidingMappingContext());
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find auth users because of some db error ", e);
        }
    }

    @Override
    public List<AuthUserDto> findAll() throws HibernateException {
        try {
            return authUserRepository.findAll().stream()
                    .map(a -> authUserMapper.authUserToDto(a, new CycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find auth users because of some db error ", e);
        }
    }

    @Override
    public AuthUserDto updateAuthUser(AuthUserDto authUserDto) throws ExistEmailException, HibernateException,
            AuthUserNotFoundException {
        try {
            Optional<AuthUser> authUser = authUserRepository.findById(authUserDto.getId());
            if (authUser.isPresent()) {
                if (isUserEmailExist(authUser.get().getId(), authUserDto.getEmail())) {
                    throw new ExistEmailException("There is an account with that email address: " + authUserDto.getEmail() +
                            " Can't update user!");
                }
                AuthUser authUserToSave = authUserMapper.authUserDtoToEntity(authUserDto, new CycleAvoidingMappingContext());
                AuthUser savedAuthUser = authUserRepository.save(authUserToSave);
                return authUserMapper.authUserToDto(savedAuthUser, new CycleAvoidingMappingContext());
            } else {
                throw new AuthUserNotFoundException("Auth user: " + authUserDto.getEmail() + " does not exist! Can't update!");
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Can't save update auth user because of some db error ", e);
        }
    }

    @Override
    public void deleteAuthUser(Long id) {

    }

    private boolean isUserEmailExist(Long id, String email) {
        return authUserRepository.findByIdNot(id)
                .stream()
                .anyMatch(e -> e.getEmail().toLowerCase().equals(email.toLowerCase()));
    }
}

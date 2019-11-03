package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.AuthUserNotFoundException;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import com.eszwalnia.timesh.configuration.CycleAvoidingMappingContext;
import com.eszwalnia.timesh.exceptionHandler.ExistEmailException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;
    private final CreateAuthUserDtoConventer createAuthUserDtoConventer;

    @Autowired
    private PasswordEncoder encode;

    @Override
    @Transactional
    public AuthUserDto createAuthUser(CreateAuthUserDto createAuthUserDto) throws HibernateException, ExistEmailException {
        if (isUserEmailExist(createAuthUserDto.getEmail())) {
            throw new ExistEmailException("There is an account with that email address: " + createAuthUserDto.getEmail() +
                    " Can't CREATE user!");
        }
        //todo jak bedzie z frontu to wtedy wywalic ten zapis
        createAuthUserDto.setCreatedDate(LocalDateTime.now().withNano(0));
        AuthUser createdAuthUser = createAuthUserDtoConventer.convertToAuthUser(createAuthUserDto);
        createdAuthUser.setEnabled(true);
        createdAuthUser.setPassword(encode.encode(createAuthUserDto.getPassword()));
        createdAuthUser.setMatchPassword(encode.encode(createAuthUserDto.getMatchPassword()));
        try {

            authUserRepository.save(createdAuthUser);
            log.info("Created auth user: " + "\nID-> " + createdAuthUser.getId() + "\nEmail->" + createdAuthUser.getEmail());
            log.debug("Created auth user: {} \n {} \n {}", createdAuthUser, createdAuthUser.getAuthUserRoleSet(), createdAuthUser.getEmployee());
            return authUserMapper.authUserToDto(createdAuthUser, new CycleAvoidingMappingContext());
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find auth users because of some db error ", e);
        }
    }

    @Override
    public List<AuthUserDto> findAll() throws HibernateException {
        try {
            List<AuthUserDto> authUserDtoList = authUserRepository.findAll().stream()
                    .map(a -> authUserMapper.authUserToDto(a, new CycleAvoidingMappingContext()))
                    .collect(Collectors.toList());
            log.info("Found -> " + authUserDtoList.size() + " auth users in data base");
            return authUserDtoList;
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find auth users because of some db error ", e);
        }
    }

    @Override
    public AuthUserDto updateAuthUser(AuthUserDto authUserDto) throws ExistEmailException, HibernateException,
            AuthUserNotFoundException {
        log.debug("Update data for auth user: {} \n {} \n {}", authUserDto, authUserDto.getAuthUserRoleSet(), authUserDto.getEmployee());
        try {
            Optional<AuthUser> authUser = authUserRepository.findById(authUserDto.getId());
            if (authUser.isPresent()) {
                AuthUser foundAuthUser = authUser.get();
                log.debug("Old data for auth user: {} \n {} \n {}", foundAuthUser, foundAuthUser.getAuthUserRoleSet(), foundAuthUser.getEmployee());
                if (isUserEmailExist(foundAuthUser.getId(), authUserDto.getEmail())) {
                    throw new ExistEmailException("There is an account with that email address: " + authUserDto.getEmail() +
                            ". Can't update user!");
                }
                AuthUser authUserToSave = authUserMapper.authUserDtoToEntity(authUserDto, new CycleAvoidingMappingContext());
                AuthUser savedAuthUser = authUserRepository.save(authUserToSave);
                log.info("Auth user: " + "\nID-> " + savedAuthUser.getId() + "\nEmail->" + savedAuthUser.getEmail() + " UPDATED!");
                return authUserMapper.authUserToDto(savedAuthUser, new CycleAvoidingMappingContext());
            } else {
                throw new AuthUserNotFoundException("Auth user: " + authUserDto.getEmail() + " does not exist! Can't update!");
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Can't update auth user because of some db error ", e);
        }
    }

    @Override
    public boolean deleteAuthUser(Long id) throws AuthUserNotFoundException, HibernateException {
        boolean isDeleted;
        try {
            if (authUserRepository.existsById(id)) {
                log.warn("Going to delete auth user id: " + id);
                authUserRepository.deleteById(id);
                isDeleted = true;
                log.info("Auth user id: " + id + " deleted!");
                ;
            } else {
                throw new AuthUserNotFoundException("Auth user id: " + id + " isn't exist. Cant' delete");
            }
        } catch (HibernateException e) {
            throw new RuntimeException("Can't save update auth user because of some db error ", e);
        }
        return isDeleted;
    }

    //todo-> if user logged get his ID and try to find glupie to jest.
    private boolean isUserEmailExist(Long id, String email) {
        return authUserRepository.findByIdNot(id)
                .stream()
                .anyMatch(e -> e.getEmail().toLowerCase().equals(email.toLowerCase()));
    }

    private boolean isUserEmailExist(String email) {
        return isUserEmailExist(0L, email);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)//beacuse of lazy collection
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUserRepositoryByEmail = authUserRepository.findByEmail(s);
        AuthUserDto authUserDto = authUserMapper.authUserToDto(authUserRepositoryByEmail, new CycleAvoidingMappingContext());
        return authUserDto;
    }
}

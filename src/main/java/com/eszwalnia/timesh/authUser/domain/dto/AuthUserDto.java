package com.eszwalnia.timesh.authUser.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.eszwalnia.timesh.employee.domain.dto.EmployeeDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AuthUserDto implements UserDetails {

    //todo make validation on fields -> form email own validation.
    //todo zrobic date aby byla UTF
    // zrobic createUserDto bez ID,
    private Long id;
    private String email;
    private String password;
    private String matchPassword;
    private LocalDateTime createdDate;
    private LocalDateTime firstLogon;
    private LocalDateTime lastLogon;
    private boolean isEnabled;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<AuthUserRoleDto> authUserRoleSet = new HashSet<>();
    private EmployeeDto employee;


    public AuthUserDto(String email, String password, String matchPassword) {
        this.email = email;
        this.password = password;
        this.matchPassword = matchPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthUserRoleSet().stream()
                .map(AuthUserRoleDto::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }
}

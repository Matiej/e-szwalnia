package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.employee.domain.Employee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AUTH_USER")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUH_ID")
    private Long id;
    @Column(name = "AUH_EMAIL")
    private String email;
    @Column(name = "AUH_PASSWORD")
    private String password;
    @Column(name = "AUH_MATCH_PASSWORD")
    private String matchPassword;
    @Column(name = "AUH_CREATE_DATE")
    private LocalDateTime createdDate;
    @Column(name = "AUH_FIRST_LOGON")
    private LocalDateTime firstLogon;
    @Column(name = "AUH_LAST_LOGON")
    private LocalDateTime lastLogon;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "AUH_ID", referencedColumnName = "AUH_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROL_ID", referencedColumnName = "ROL_ID")})
    private Set<AuthUserRole> authUserRoleSet = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(mappedBy = "authUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    private Employee employee;
}

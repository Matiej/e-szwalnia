package com.eszwalnia.timesh.authUser.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "AUTH_USER_ROLE")
public class AuthUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROL_ID")
    private Long id;
    @Column(name = "ROL_NAME")
    private String name;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "authUserRoleSet", targetEntity = AuthUser.class)
    private Set<AuthUser> authUserSet = new HashSet<>();

}

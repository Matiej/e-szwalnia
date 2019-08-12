package com.eszwalnia.timesh.authUser.domain;

import com.eszwalnia.timesh.authUser.domain.AuthUserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRoleRepository extends CrudRepository<AuthUserRole, Long> {
}

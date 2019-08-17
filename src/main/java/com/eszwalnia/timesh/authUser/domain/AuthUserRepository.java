package com.eszwalnia.timesh.authUser.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    List<AuthUser> findByIdNot(Long id);
}

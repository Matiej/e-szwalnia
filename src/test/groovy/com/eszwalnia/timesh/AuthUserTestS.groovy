package com.eszwalnia.timesh

import com.eszwalnia.timesh.authUser.AuthUserService
import com.eszwalnia.timesh.authUser.domain.AuthUserServiceImpl
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserRoleDto
import spock.lang.Specification

class AuthUserTestS extends Specification {

    private AuthUserService authUserService
    private List<AuthUserDto> createAuthUserDtoList

    def setup() {
        authUserService = new AuthUserServiceImpl()
        AuthUserRoleDto admin = new AuthUserRoleDto("ADMIN")
        AuthUserRoleDto user = new AuthUserRoleDto("USER")
        AuthUserDto newUser1 = new AuthUserDto("matiej@matiej.pl", "pass", "pass")
        newUser1.getAuthUserRoleDtoSet().addAll(Arrays.asList(admin,user))
        AuthUserDto newUser2 = new AuthUserDto("matiej@user.pl", "pass", "pass")
        newUser2.getAuthUserRoleDtoSet().addAll(Arrays.asList(admin))
        AuthUserDto newUser3 = new AuthUserDto("matiej@admin.pl", "pass", "pass")
        newUser3.getAuthUserRoleDtoSet().addAll(Arrays.asList(user))
        createAuthUserDtoList = Arrays.asList(newUser1,newUser2,newUser3)

    }

    def "should create auth user and return from db with id"() {
        given:"sample auth user from the setup list"
        def expectResult = createAuthUserDtoList.get(0)
        def expectUserEmail = createAuthUserDtoList.get(0).getEmail()

        when: "first user from the test list saved into db"
        def realResult = authUserService.createAuthUser(expectResult)
        and:
        def realResultByEmail = authUserService.findUserByEmail(expectUserEmail)

        then: "realResult from createAuthUser method should return the same user"
        realResult == expectResult
        and: "realResult user should have id from data base"
        realResult.getId() != null
        and: "realLogin from findUserByLogin should return same user as createAuthUser method"
        realResult == realResultByEmail

    }

    def close() {
        given: "cleaning inMemoryDataBase"
        //some code
    }
}


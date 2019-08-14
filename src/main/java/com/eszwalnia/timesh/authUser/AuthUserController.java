package com.eszwalnia.timesh.authUser;

import com.eszwalnia.timesh.authUser.domain.AuthUserService;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Api(description = "User authorization crud controller")
@RequestMapping("/authuser")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthUserController {

    private final AuthUserService authUserService;

    @PostMapping("/create")
    @ApiOperation(value = "Create new authorization user", response = AuthUserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Auth user created and saved to data base successful"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! Auth user not created!"),
            @ApiResponse(code = 409, message = "Auth user already exists, can't create"),
            @ApiResponse(code = 503, message = "Data base server error. Can't create auth user.")})
    ResponseEntity<Object> create(@RequestBody @Valid CreateAuthUserDto createAuthUserDto) {
        AuthUserDto createdAuthUser = authUserService.createAuthUser(createAuthUserDto);
        URI savedUri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .query("id={id}")
                .buildAndExpand(createdAuthUser.getId())
                .toUri();
        return ResponseEntity.created(savedUri)
                .body(createdAuthUser);
    }

    @GetMapping("/findall")
    @ApiOperation(value = "Find all authUsers in data base", response = AuthUserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 503, message = "Server error. Can't get any users."),
            @ApiResponse(code = 200, message = "All users loaded from db successful."),
//            @ApiResponse(code = 400, message = "No measuring stations found!"),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!")})
    ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(authUserService.findAll());
    }

}

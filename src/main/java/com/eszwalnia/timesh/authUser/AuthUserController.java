package com.eszwalnia.timesh.authUser;

import com.eszwalnia.timesh.authUser.domain.AuthUserService;
import com.eszwalnia.timesh.authUser.domain.dto.AuthUserDto;
import com.eszwalnia.timesh.authUser.domain.dto.CreateAuthUserDto;
import io.swagger.annotations.*;
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

    //todo do poprawy prawdopodobnie-> z forntu będzie brany emial lub login i dopiero z formatki
    //todo updejt
    @PutMapping("/update")
    @ApiOperation(value = "Update auth user in data base", response = AuthUserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Auth user updated successful"),
            @ApiResponse(code = 401, message = "No remote user found or not user logged in"),
            @ApiResponse(code = 404, message = "Auth user does not exist! Can't update!"),
            @ApiResponse(code = 409, message = "Email/login already exists, can't update"),
            @ApiResponse(code = 503, message = "Data base server error. Can't update auth user.")})
    ResponseEntity<Object> update(@RequestBody @Valid AuthUserDto authUserDto) {
        AuthUserDto updatedAuthUser = authUserService.updateAuthUser(authUserDto);
        return ResponseEntity.ok(updatedAuthUser);
    }

    @GetMapping("/findall")
    @ApiOperation(value = "Find all authUsers in data base", response = AuthUserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users loaded from db successful."),
            @ApiResponse(code = 404, message = "Server has not found anything matching the requested URI! No users found!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any users."),})
    ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(authUserService.findAll());
    }

    @DeleteMapping("delte")
    @ApiOperation(value = "Delete authUser by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All users loaded from db successful."),
            @ApiResponse(code = 404, message = "Auth user does not exist! Can't update!"),
            @ApiResponse(code = 503, message = "Server error. Can't get any users."),})
    @ApiImplicitParam(required = true, name = "User Id", value = "Enter user Id", dataType = "Long")
    ResponseEntity<Object> delete(@RequestParam("id") Long id) {
        authUserService.deleteAuthUser(id);
        return ResponseEntity.ok("Auth user id: " + id + " deleted successful");
    }

}

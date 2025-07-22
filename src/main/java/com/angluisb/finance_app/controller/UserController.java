package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}

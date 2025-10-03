package com.angluisb.finance_app.controller;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.dto.update.UserUpdate;
import com.angluisb.finance_app.dto.update.UserUpdatePassword;
import com.angluisb.finance_app.service.UserService;
import com.angluisb.finance_app.service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest request) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody @Valid UserUpdate request, @PathVariable Long id) throws BadRequestException {
        return ResponseEntity.ok(userService.updateUser(request,id));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePasswordUser(@RequestBody @Valid UserUpdatePassword request, @PathVariable Long id) throws BadRequestException {
        userService.updatePassword(request,id);
        return ResponseEntity.ok("Password Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws BadRequestException {
        userService.delete(id);
        return ResponseEntity.ok("Deleted");
    }
}

package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.entity.Enum.RolesType;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.mapper.UserMapper;
import com.angluisb.finance_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final  UserRepository userRepository;
    private final  UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        user.setRole(RolesType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }
}

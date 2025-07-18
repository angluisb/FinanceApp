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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final  UserRepository userRepository;
    private final  UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        user.setRole(RolesType.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public User getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }
        return userOptional.get();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserResponse updateUser(UserRequest user, Long id) {
        return null;
    }
}

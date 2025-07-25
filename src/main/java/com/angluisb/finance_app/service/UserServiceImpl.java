package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.entity.Enum.RolesType;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.exception.BusinessException;
import com.angluisb.finance_app.exception.ValidationException;
import com.angluisb.finance_app.mapper.UserMapper;
import com.angluisb.finance_app.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public UserResponse createUser(UserRequest request) throws BadRequestException {
        if (request == null) {
            throw new ValidationException("UserRequest cannot be null");
        }

        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new BusinessException("Email is already in use");
        }

        User user = userMapper.toEntity(request);
        user.setRole(RolesType.USER);
        user.setEmail(normalizedEmail);
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

package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.dto.update.UserUpdate;
import com.angluisb.finance_app.dto.update.UserUpdatePassword;
import com.angluisb.finance_app.entity.Enum.RolesType;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.exception.BusinessException;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
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
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        return userOptional.get();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id must not be null");
        }

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }

        else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }


    @Override
    public UserResponse updateUser(UserUpdate userRequest, Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id must not be null");
        }

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        User userToUpdate = optionalUser.get();

        String normalizedEmail = userRequest.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmailAndIdNot(normalizedEmail,id)) {
            throw new BusinessException("Email is already in use");
        }

        userToUpdate.setEmail(normalizedEmail);
        userToUpdate.setFirstName(userRequest.getFirstName());
        userToUpdate.setLastName(userRequest.getLastName());

        return userMapper.toUserResponse(userRepository.save(userToUpdate));
    }

    @Override
    public void updatePassword(UserUpdatePassword userUpdatePassword, Long id) {
        if (id == null){
            throw new IllegalArgumentException("User id must not be null");
        }

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        User userToUpdate = optionalUser.get();


        if (!passwordEncoder.matches(userUpdatePassword.getCurrentPassword(), userToUpdate.getPassword())) {
            throw new ValidationException("Passwords do not match");
        }

        userToUpdate.setPassword(passwordEncoder.encode(userUpdatePassword.getNewPassword()));
        userRepository.save(userToUpdate);
    }
}

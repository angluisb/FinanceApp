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
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    @Override
    @Transactional
    public UserResponse createUser(UserRequest request){
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
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }


    @Override
    @Transactional
    public UserResponse updateUser(UserUpdate userRequest, Long id) {
        User userToUpdate = getById(id);

        if (userRequest.getEmail() != null) {
            String normalizedEmail = userRequest.getEmail().trim().toLowerCase();

            if (userRepository.existsByEmailAndIdNot(normalizedEmail,id)) {
                throw new BusinessException("Email is already in use");
            }
            userToUpdate.setEmail(normalizedEmail);
        }


        Optional.ofNullable(userRequest.getFirstName()).ifPresent(userToUpdate::setFirstName);
        Optional.ofNullable(userRequest.getLastName()).ifPresent(userToUpdate::setLastName);

        return userMapper.toUserResponse(userRepository.save(userToUpdate));
    }

    @Override
    @Transactional
    public void updatePassword(UserUpdatePassword userUpdatePassword, Long id) {
        User userToUpdate = getById(id);

        if (!passwordEncoder.matches(userUpdatePassword.getCurrentPassword(), userToUpdate.getPassword())) {
            throw new ValidationException("Passwords do not match");
        }
        userToUpdate.setPassword(passwordEncoder.encode(userUpdatePassword.getNewPassword()));
        userRepository.save(userToUpdate);
    }
}
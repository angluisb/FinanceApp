package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.dto.update.UserUpdate;
import com.angluisb.finance_app.dto.update.UserUpdatePassword;
import com.angluisb.finance_app.entity.User;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface UserService {
    public UserResponse createUser(UserRequest user) throws BadRequestException;
    public User getById(Long id);
    void delete(Long id);
    public UserResponse updateUser(UserUpdate user, Long id);
    public void updatePassword(UserUpdatePassword user, Long id);

}

package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.UserRequest;
import com.angluisb.finance_app.dto.response.UserResponse;
import com.angluisb.finance_app.entity.User;

public interface UserService {
    public UserResponse createUser(UserRequest user);
    public User getById(Long id);
    void delete(Long id);
    public UserResponse updateUser(UserRequest user, Long id);
}

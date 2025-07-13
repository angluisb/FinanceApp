package com.angluisb.finance_app.dto.request;

import com.angluisb.finance_app.entity.Enum.RolesType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {

    @Size(min = 1, max = 50)
    @NotNull(message = "Firstname is Required")
    private String firstName;

    @Size(min = 1, max = 50)
    @NotNull(message = "Lastname is Required")
    private String lastName;

    @Email
    @Size(max = 100)
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 225)
    private String password;

    @NotNull(message = "Role is required")
    private RolesType role;
}

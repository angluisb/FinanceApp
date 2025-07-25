package com.angluisb.finance_app.dto.request;

import com.angluisb.finance_app.entity.Enum.RolesType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {

    @Size(min = 1, max = 50, message = "First name size must be between 1 and 50")
    @NotNull(message = "First name is Required")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name size must be between 1 and 50" )
    @NotNull(message = "Last name is Required")
    private String lastName;

    @Email
    @Size(max = 100)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 225, message = "Password size must be between 8 and 225")
    private String password;


}

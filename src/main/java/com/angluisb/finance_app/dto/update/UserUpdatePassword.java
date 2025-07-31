package com.angluisb.finance_app.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdatePassword {
    @NotBlank(message = "Current Password is required")
    private String currentPassword;

    @NotBlank(message = "New Password is required")
    @Size(min = 8, max = 225, message = "Password size must be between 8 and 225")
    private String newPassword;
}

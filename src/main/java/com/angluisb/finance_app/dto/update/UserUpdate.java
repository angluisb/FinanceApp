package com.angluisb.finance_app.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdate {
    @Size(min = 1, max = 50, message = "First name size must be between 1 and 50")
    private String firstName;

    @Size(min = 1, max = 50, message = "Last name size must be between 1 and 50" )
    private String lastName;

    @Email
    @Size(max = 100)
    private String email;
}

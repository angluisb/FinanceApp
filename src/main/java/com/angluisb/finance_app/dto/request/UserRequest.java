package com.angluisb.finance_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequest {

    @Size(min = 1, max = 50)
    @NotNull
    private String firstName;

    @Size(min = 1, max = 50)
    @NotNull
    private String lastName;

    @Email
    @Size(max = 100)
    @NotNull
    private String email;

    @NotNull
    @Size(min = 8, max = 225)
    private String password;

}

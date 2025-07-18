package com.angluisb.finance_app.dto.summary;

import com.angluisb.finance_app.entity.Enum.RolesType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserSummary {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private RolesType role;
}

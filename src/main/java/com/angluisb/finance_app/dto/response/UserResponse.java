package com.angluisb.finance_app.dto.response;

import com.angluisb.finance_app.dto.summary.WalletSummary;
import com.angluisb.finance_app.entity.Enum.RolesType;
import com.angluisb.finance_app.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private RolesType role;
    private LocalDate createdAt;
    private List<WalletSummary> wallets;
}

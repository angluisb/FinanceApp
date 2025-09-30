package com.angluisb.finance_app.repository;

import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAllByUser(User user);

    boolean existsByNameAndUser(@NotNull(message = "Name is required") @Size(min = 3, max = 50, message = "name size must be between 3 and 50") String name, User user);
}

package com.angluisb.finance_app.repository;

import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAllByUser(User user);
}

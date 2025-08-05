package com.angluisb.finance_app.repository;

import com.angluisb.finance_app.entity.Enum.CategoryType;
import com.angluisb.finance_app.entity.Transaction;
import com.angluisb.finance_app.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWallet(Wallet wallet);

    List<Transaction> findAllByWalletAndCategory(Wallet wallet, CategoryType category);

    List<Transaction> findAllByWalletAndDate(Wallet wallet, LocalDate date);

    List<Transaction> findAllByWalletAndDateBetween(Wallet wallet, LocalDate dateStart, LocalDate dateEnd);

    boolean existsByWallet_Id(Long walletId);

    List<Transaction> findAllByWalletId(Long walletId);

}

package com.angluisb.finance_app.repository;

import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import com.angluisb.finance_app.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findAllByWallet(Wallet wallet);


    List<Goal> findAllByWalletAndStatus(Wallet wallet, Boolean status);

}

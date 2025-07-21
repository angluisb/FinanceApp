package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.mapper.GoalMapper;
import com.angluisb.finance_app.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService{

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final WalletServiceImpl walletService;



    @Override
    public GoalResponse create(GoalRequest requestGoal) {
        Goal newGoal = goalMapper.toGoal(requestGoal);
        newGoal = goalRepository.save(newGoal);

        Wallet fullWallet = walletService.getById(requestGoal.getWalletId());
        newGoal.setWallet(fullWallet);

        return goalMapper.toGoalResponse(newGoal);
    }

    @Override
    public GoalResponse update(GoalRequest goal) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public GoalResponse findById(Long id) {
        return null;
    }

    @Override
    public List<GoalResponse> findByWalletId(Long id) {
        return List.of();
    }

    @Override
    public List<GoalResponse> findByStatus(Boolean status) {
        return List.of();
    }
}

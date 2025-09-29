package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
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

        Wallet fullWallet = walletService.getById(requestGoal.getWalletId());
        newGoal.setWallet(fullWallet);
        Goal savedGoal = goalRepository.save(newGoal);


        return goalMapper.toGoalResponse(savedGoal);
    }

    @Override
    public GoalResponse update(GoalRequest goal) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Goal findById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Goal not found with id: " +id));
    }

    @Override
    public List<GoalResponse> findByWalletId(Long id) {
        Wallet wallet = walletService.getById(id);
        
        List<Goal> goals = goalRepository.findAllByWallet(wallet);

        return goalMapper.toGoalResponse(goals);
    }

    @Override
    public List<GoalResponse> findByStatus(Boolean status, Long walletId) {
        Wallet wallet = walletService.getById(walletId);

        List<Goal> goalsByStatus = goalRepository.findAllByWalletAndStatus(wallet, status);
        return goalMapper.toGoalResponse(goalsByStatus);
    }
}

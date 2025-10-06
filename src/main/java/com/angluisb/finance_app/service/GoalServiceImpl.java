package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import com.angluisb.finance_app.entity.Wallet;
import com.angluisb.finance_app.exception.ResourceNotFoundException;
import com.angluisb.finance_app.mapper.GoalMapper;
import com.angluisb.finance_app.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GoalServiceImpl implements GoalService{

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final WalletService walletService;



    @Override
    @Transactional
    public GoalResponse create(GoalRequest goalRequest) {
        Wallet fullWallet = walletService.getById(goalRequest.getWalletId());
        Goal goal = goalMapper.toGoal(goalRequest);
        goal.setWallet(fullWallet);
        Goal savedGoal = goalRepository.save(goal);
        
        return goalMapper.toGoalResponse(savedGoal);
    }

    @Override
    @Transactional
    public GoalResponse update(GoalRequest goalUpdated, Long id) {
        Goal existingGoal = getById(id);

        Optional.ofNullable(goalUpdated.getName()).ifPresent(existingGoal::setName);
        Optional.ofNullable(goalUpdated.getAmount()).ifPresent(existingGoal::setAmount);
        Optional.ofNullable(goalUpdated.getDeadline()).ifPresent(existingGoal::setDeadline);
        Optional.ofNullable(goalUpdated.getDescription()).ifPresent(existingGoal::setDescription);
        
        return goalMapper.toGoalResponse(goalRepository.save(existingGoal));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Goal goal = getById(id);
        goalRepository.delete(goal);
    }

    @Override
    public Goal getById(Long id) {
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
        if (status == null){
            throw new IllegalArgumentException("status is null");
        }
        Wallet wallet = walletService.getById(walletId);

        List<Goal> goalsByStatus = goalRepository.findAllByWalletAndStatus(wallet, status);
        return goalMapper.toGoalResponse(goalsByStatus);
    }
}

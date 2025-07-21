package com.angluisb.finance_app.service;

import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;

import java.util.List;

public interface GoalService {
    public GoalResponse create(GoalRequest goal);
    public GoalResponse update(GoalRequest goal);
    public void delete(Long id);
    public GoalResponse findById(Long id);
    List<GoalResponse> findByWalletId(Long id);
    List<GoalResponse> findByStatus(Boolean status);


}

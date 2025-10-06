package com.angluisb.finance_app.controller;


import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.service.GoalService;
import com.angluisb.finance_app.service.GoalServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/goal")
public class GoalController {

    private final GoalService goalService;



    @PostMapping
    public ResponseEntity<GoalResponse> createGoal (@RequestBody @Valid GoalRequest goalRequest) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(goalService.create(goalRequest));
    }
    @PutMapping("/{id}")
    public ResponseEntity<GoalResponse> updateGoal(@RequestBody @Valid GoalRequest goalRequest, @PathVariable Long id) {
        return  ResponseEntity.status(HttpStatus.OK).body(goalService.update(goalRequest,id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<GoalResponse>> getGoals(@PathVariable Long id) {
        List<GoalResponse> goalResponses = goalService.findByWalletId(id);
        return ResponseEntity.ok(goalResponses);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        goalService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

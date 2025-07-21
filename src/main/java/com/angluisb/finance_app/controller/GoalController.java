package com.angluisb.finance_app.controller;


import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.service.GoalService;
import com.angluisb.finance_app.service.GoalServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/goal")
public class GoalController {

    private final GoalServiceImpl goalService;



    @PostMapping("new")
    public ResponseEntity<GoalResponse> createGoal (@RequestBody @Valid GoalRequest goalRequest) {

        GoalResponse goal =  goalService.create(goalRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(goal);
    }

}

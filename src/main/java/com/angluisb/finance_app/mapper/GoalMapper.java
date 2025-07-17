package com.angluisb.finance_app.mapper;

import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    GoalResponse toGoalResponse(Goal goal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Goal toGoal(Goal goal);
}

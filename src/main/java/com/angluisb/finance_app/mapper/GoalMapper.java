package com.angluisb.finance_app.mapper;

import com.angluisb.finance_app.dto.request.GoalRequest;
import com.angluisb.finance_app.dto.response.GoalResponse;
import com.angluisb.finance_app.entity.Goal;
import com.angluisb.finance_app.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GoalMapper {
    GoalResponse toGoalResponse(Goal goal);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "wallet", source = "walletId", qualifiedByName = "mapWalletIdToWallet")
    Goal toGoal(GoalRequest goalRequest);

    @Named("mapWalletIdToWallet")
    static Wallet mapWalletIdToWallet(Long walletId) {
        if (walletId == null) {
            return null;
        }
        Wallet wallet = new Wallet();
        wallet.setId(walletId);
        return wallet;
    }

}

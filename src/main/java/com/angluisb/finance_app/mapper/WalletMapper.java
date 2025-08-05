package com.angluisb.finance_app.mapper;

import com.angluisb.finance_app.dto.request.WalletRequest;
import com.angluisb.finance_app.dto.response.WalletResponse;
import com.angluisb.finance_app.dto.summary.UserSummary;
import com.angluisb.finance_app.dto.summary.WalletSummary;
import com.angluisb.finance_app.entity.User;
import com.angluisb.finance_app.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WalletMapper {


    WalletResponse toWalletResponse(Wallet wallet);

    List<WalletResponse> toWalletResponse(List<Wallet> walletList);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    Wallet toEntity(WalletRequest walletRequest);

    @Named("mapUserIdToUser")
    static User mapUserIdToUser(Long userId) {
        if (userId == null) {
            return null;
        }
        User user = new User();
        user.setId(userId);
        return user;
    }

    WalletSummary toWalletSummary(Wallet wallet);
}


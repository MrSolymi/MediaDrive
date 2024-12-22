package me.solymi.service;

import me.solymi.dto.CreateInviteResponse;
import me.solymi.model.InviteToken;
import me.solymi.model.User;

import java.util.List;

public interface InviteService {

    CreateInviteResponse invite(User user);

    InviteToken getToken(String token);

    boolean isValid(InviteToken token);

    void useToken(InviteToken token);

    void saveToken(InviteToken token);

    void deleteToken(String token);

    List<InviteToken> getAllInviteTokens(User user);
}

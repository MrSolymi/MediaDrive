package me.solymi.service;

import me.solymi.dto.CreateInviteResponse;
import me.solymi.model.InviteToken;
import me.solymi.model.User;

public interface InviteService {

    CreateInviteResponse invite(User user);

    InviteToken getToken(String token);

    boolean isValid(InviteToken token);

    void useToken(InviteToken token);

    void saveToken(InviteToken token);
}

package me.solymi.service.impl;

import lombok.RequiredArgsConstructor;
import me.solymi.dto.CreateInviteResponse;
import me.solymi.model.InviteToken;
import me.solymi.model.User;
import me.solymi.repository.InviteTokenRepo;
import me.solymi.service.InviteService;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InviteServiceImpl implements InviteService {

    private final InviteTokenRepo inviteTokenRepo;

    @Override
    public CreateInviteResponse invite(User user) {
        var token = new InviteToken(user);

        inviteTokenRepo.save(token);

        return new CreateInviteResponse(token.getToken());
    }

    @Nullable
    @Override
    public InviteToken getToken(String token) {
        return inviteTokenRepo.findByToken(token);
    }

    @Override
    public boolean isValid(InviteToken token) {
        token.validateToken();
        saveToken(token);
        return token.isValid();
    }

    @Override
    public void useToken(InviteToken token) {
        token.useToken();
    }

    @Override
    public void saveToken(InviteToken token) {
        inviteTokenRepo.save(token);
    }

    @Override
    public void deleteToken(String token) {
        var tokenForDelete = inviteTokenRepo.findByToken(token);

        inviteTokenRepo.delete(tokenForDelete);
    }

    @Override
    public List<InviteToken> getAllInviteTokens(User user) {
        return inviteTokenRepo.findAllByUser(user);
    }

}

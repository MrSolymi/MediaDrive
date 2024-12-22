package me.solymi.controller;

import lombok.RequiredArgsConstructor;
import me.solymi.annotations.LoggedIn;
import me.solymi.dto.DeleteInviteTokenRequest;
import me.solymi.model.InviteToken;
import me.solymi.service.InviteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/invite", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InviteTokenController extends AbstractController{

    private final InviteService inviteService;

    @LoggedIn
    @PostMapping(path = "/create")
    public ResponseEntity<?> invite() {
        var user = getCurrentUser();

        var token = inviteService.invite(user);

        return ResponseEntity.ok(token);
    }

    @LoggedIn
    @PostMapping(path = "/delete")
    public ResponseEntity<?> deleteInvite(@RequestBody DeleteInviteTokenRequest tokenRequest) {
        inviteService.deleteToken(tokenRequest.token());

        return ResponseEntity.ok("{}");
    }

    @LoggedIn
    @PostMapping(path = "/all_invites")
    public ResponseEntity<?> getAllInvites() {
        var user = getCurrentUser();

        var invites = inviteService.getAllInviteTokens(user);

        List<String> tokens = new ArrayList<>();
        for (InviteToken invite : invites) {
            tokens.add(invite.getToken());
        }

        return ResponseEntity.ok(tokens);
    }
}

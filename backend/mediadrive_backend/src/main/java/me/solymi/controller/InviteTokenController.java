package me.solymi.controller;

import lombok.RequiredArgsConstructor;
import me.solymi.annotations.LoggedIn;
import me.solymi.service.InviteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/invite", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class InviteTokenController extends ProfileController{

    private final InviteService inviteService;

    @LoggedIn
    @PostMapping(path = "/create")
    public ResponseEntity<?> invite() {
        var user = getCurrentUser();

        var token = inviteService.invite(user);

        return ResponseEntity.ok(token);
    }
}

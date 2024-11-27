package me.solymi.controller;

import me.solymi.annotations.LoggedIn;
import me.solymi.dto.ProfileInfoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController extends AbstractController {

    @LoggedIn
    @GetMapping(path = "/info")
    public ProfileInfoResponse getProfileInfo() {
        var user = getCurrentUser();
        if (user == null) throw new RuntimeException("User not found");

        return new ProfileInfoResponse(user.getUsername(), user.getEmail(), user.getRole());
    }
}

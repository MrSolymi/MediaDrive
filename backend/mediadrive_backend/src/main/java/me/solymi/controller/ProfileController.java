package me.solymi.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.solymi.annotations.LoggedIn;
import me.solymi.dto.AllUsersResponse;
import me.solymi.dto.ProfileInfoResponse;
import me.solymi.model.User;
import me.solymi.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfileController extends AbstractController {

    private final UserService userService;

    @LoggedIn
    @GetMapping(path = "/info")
    public ResponseEntity<?> getProfileInfo() {
        var user = getCurrentUser();

        Object data = new ProfileInfoResponse(user.getUsername(), user.getEmail(), user.getRole());

        return ResponseEntity.ok(data);
    }

    @LoggedIn
    @GetMapping(path = "/all_users")
    public ResponseEntity<?> getAllUsers() {
        var users = userService.getAllUsers();

        List<String> usersList = new ArrayList<>();
        for (User user : users) {
            usersList.add(user.getUsername());
        }

        return ResponseEntity.ok(usersList);
    }
}

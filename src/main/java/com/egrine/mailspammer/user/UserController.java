package com.egrine.mailspammer.user;

import com.egrine.mailspammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailspammer.user.DTO.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
    private final UserService service;

    @PostMapping()
    public SecureUserProfileDTO registerNewUser(@RequestBody UserProfileDTO newUser) {
        return service.addUser(newUser);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId, @AuthenticationPrincipal User authenticatedUser) {
        service.deleteUser(userId, authenticatedUser);
    }
}

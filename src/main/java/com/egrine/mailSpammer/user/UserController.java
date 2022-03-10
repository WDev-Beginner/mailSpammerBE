package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
    private final UserService service;

    @PostMapping()
    public SecureUserProfileDTO registerNewUser(@RequestBody UserProfileDTO newUser){
        return service.addUser(newUser);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        service.deleteUser(userId);
    }
}

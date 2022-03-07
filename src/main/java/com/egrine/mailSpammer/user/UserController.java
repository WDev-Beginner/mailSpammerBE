package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
    private final UserService service;

    @PostMapping("/register")
    public void registerNewUser(@RequestBody UserProfileDTO newUser){
        service.addUser(newUser);
    }

}

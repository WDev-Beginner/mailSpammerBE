package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class UserController {
    private final UserService service;

    @PostMapping("/add-user")
    public Object registerNewUser(@RequestBody UserProfileDTO newUser){
        if(service.getUserProfileByEmail(newUser.getEmailAddress()) != null){
            return new ResponseEntity<>(
                    "User with provided email already exists",
                    HttpStatus.CONFLICT);
        }
        return service.addUser(newUser);
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        service.deleteUser(userId);
    }



}

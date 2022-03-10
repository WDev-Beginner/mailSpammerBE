package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import com.egrine.mailSpammer.utilities.UserAlreadyExistException;
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

    @PostMapping()
    public Object registerNewUser(@RequestBody UserProfileDTO newUser){
        return service.addUser(newUser);// todo => CREATE DTO with no password
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        service.deleteUser(userId); // todo => add password encryption in the backend
    }



}

package com.egrine.mailSpammer.email;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/templates")
@AllArgsConstructor
public class EmailController {
    private final EmailServiceImp service;

    // user email template endpoints
    // todo -> add some user session handling to handle these data requests.
    // todo -> create exception classes to handle different api http responses

    // todo => this returns all the json versions of the templates

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<EmailTemplate> getAllUserTemplates(){
        // return all the templates belonging to the logged-in user
        return new ArrayList<>();
    }

    // todo => update both the html and the json versions

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    EmailTemplate addUserTemplate(@RequestBody EmailTemplate newEmailTemplate){
        // add the email to the user template list
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("")
    EmailTemplate updateUserTemplate(@RequestBody EmailTemplate updatedEmailTemplate){
        // update the email template here
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("")
    void deleteUserTemplate(){
        // delete the email template here
    }

    // todo => some endpoints to update only the specific recipients list




}

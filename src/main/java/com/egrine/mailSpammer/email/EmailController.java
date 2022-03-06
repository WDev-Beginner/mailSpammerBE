package com.egrine.mailSpammer.email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/templates")
class EmailController {

    private final EmailService service;

    @Autowired
    public EmailController(EmailService service){
        this.service = service;
    }

    // user email template endpoints
    // todo -> add some user session handling to handle these data requests
    // todo -> create exception classes to handle different api http responses
    // todo -> this returns all the json versions of the templates

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<EmailTemplate> getAllUserTemplates(){

        return new ArrayList<>();
    }

    // todo -> update both the html and the json versions

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    EmailTemplate addUserTemplate(@RequestBody EmailTemplate newEmailTemplate){
        // add the email to the user template list
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping()
    EmailTemplate updateUserTemplate(@RequestBody EmailTemplate updatedEmailTemplate){
        // update the email template here
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping()
    void deleteUserTemplate(){
        // delete the email template here
    }
}

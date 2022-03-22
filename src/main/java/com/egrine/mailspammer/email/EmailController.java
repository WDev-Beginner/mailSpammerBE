package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/templates")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmailController {

    private final EmailService service;



    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<EmailTemplate> getAllUserTemplates() {

        return new ArrayList<>();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    EmailTemplate addUserTemplate(@RequestBody EmailTemplateDTO newEmailTemplate) {
        // add the email to the user template list
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    EmailTemplate updateUserTemplate(@RequestBody EmailTemplateDTO updatedEmailTemplate, @PathVariable("id") String templateId) {
        // update the email template here
        return new EmailTemplate();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteUserTemplate(@PathVariable("id") Long templateId) {
        // delete the email template here
        // noreply@mailspammer.com //
    }
}

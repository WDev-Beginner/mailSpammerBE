package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
    List<EmailTemplate> getAllUserTemplates(@AuthenticationPrincipal User authenticatedUser) {
        return service.getAllUserEmailTemplates(authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    EmailTemplate addUserTemplate(@RequestBody EmailTemplateDTO newEmailTemplate, @AuthenticationPrincipal User authenticatedUser) {
        return service.addUserEmailTemplate(newEmailTemplate, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    EmailTemplate updateUserTemplate(@RequestBody UpdateEmailTemplateDTO updatedEmailTemplate, @PathVariable("id") Long templateId,
                                     @AuthenticationPrincipal User authenticatedUser) {
        return service.updateUserEmailTemplate(templateId, updatedEmailTemplate, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteUserTemplate(@PathVariable("id") Long templateId, @AuthenticationPrincipal User authenticatedUser) {
        service.deleteUserEmailTemplate(templateId, authenticatedUser);
    }
}

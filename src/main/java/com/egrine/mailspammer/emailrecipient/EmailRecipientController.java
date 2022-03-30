package com.egrine.mailspammer.emailrecipient;

import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.email.EmailService;
import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/{userId}/recipients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class EmailRecipientController {

    private final EmailRecipientService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<EmailRecipient> getAllUserRecipients(@PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser){
        return service.getAllUserRecipients(userId, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<EmailRecipient> getEmailRecipientsById(@PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser, List<Long> recipientIds){
        return service.getEmailRecipientsById(recipientIds);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    EmailRecipient addUserEmailRecipient(@RequestBody EmailRecipientDTO newUserEmailRecipient, @PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser){
        return service.addUserEmailRecipient(newUserEmailRecipient, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping()
    List<EmailRecipient> addUserEmailRecipientList(@RequestBody List<EmailRecipientDTO> newRecipientList, @PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser){
        return service.addRecipientsToUser(newRecipientList, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteUserRecipient(@PathVariable("id") Long recipientId, @AuthenticationPrincipal User authenticatedUser, @PathVariable String userId) {
        service.deleteUserEmailRecipient(recipientId, authenticatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    EmailRecipient updateUserRecipient(@RequestBody UpdateEmailRecipientDTO updatedEmailRecipient, @PathVariable("id") Long recipientId,
                                      @AuthenticationPrincipal User authenticatedUser, @PathVariable String userId) {
        return service.updateUserEmailRecipient(recipientId, updatedEmailRecipient, authenticatedUser);
    }

}

package com.egrine.mailspammer.emailrecipient;

import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import com.egrine.mailspammer.utility.Exceptions.ForbiddenOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @PostMapping("/add-one")
    EmailRecipient addUserEmailRecipient(@RequestBody EmailRecipientDTO newUserEmailRecipient, @PathVariable Long userId,  @AuthenticationPrincipal User authenticatedUser){
        if(!Objects.equals(newUserEmailRecipient.getOwnerId(), userId)){ // check if the path userId is the same as the recipient(owner)
            throw new ForbiddenOperationException();
        }
        return service.addUserEmailRecipient(newUserEmailRecipient, authenticatedUser, userId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/add-list")
    List<EmailRecipient> addUserEmailRecipientList(@RequestBody List<EmailRecipientDTO> newRecipientList, @PathVariable Long userId, @AuthenticationPrincipal User authenticatedUser){
        for (EmailRecipientDTO recipient : newRecipientList){
            if(!Objects.equals(recipient.getOwnerId(), userId)){ // check if the path userId is the same as the recipient(owner)
                throw new ForbiddenOperationException();
            }
        }

        return service.addRecipientsToUser(newRecipientList, authenticatedUser, userId);
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

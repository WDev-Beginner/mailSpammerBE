package com.egrine.mailspammer.emailrecipient.implementation;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.EmailService;
import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import com.egrine.mailspammer.emailrecipient.EmailRecipientRepository;
import com.egrine.mailspammer.emailrecipient.EmailRecipientService;
import com.egrine.mailspammer.user.UserService;
import com.egrine.mailspammer.utility.Exceptions.RecipientNotFoundException;
import com.egrine.mailspammer.utility.Exceptions.TemplateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailRecipientService implements EmailRecipientService {
    EmailRecipientRepository repository;
    UserService userService;
    EmailService emailService;

    @Override
    public List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds) {
        List<EmailRecipient> recipients = repository.getEmailRecipientsById(recipientIds);
        return recipients == null ? Collections.emptyList() : recipients;
    }

    @Override
    public List<EmailRecipient> getAllUserRecipients(Long userId, User user) {
        userService.checkAuthority(userId, user);
        List<EmailRecipient> recipients = repository.getAllUserRecipients(userId);
        return recipients == null ? Collections.emptyList() : recipients;
    }

    @Override
    public EmailRecipient getEmailRecipient(Long emailRecipientId, User user) {
        userService.checkAuthority(this.getEmailRecipientOwnerById(emailRecipientId), user);
        return repository.getEmailRecipientById(emailRecipientId);
    }

    @Override
    public Long getEmailRecipientOwnerById(Long emailRecipientId) {
        return repository.getEmailRecipientOwnerById(emailRecipientId);
    }

    @Override
    public EmailRecipient addUserEmailRecipient(EmailRecipientDTO newUserEmailRecipient, User user) {
        //userService.checkAuthority(newUserEmailRecipient.getOwnerId(), user);

        EmailRecipient userEmailRecipientToAdd = new EmailRecipient(newUserEmailRecipient,
                userService.getUserProfileById(newUserEmailRecipient.getOwnerId()),
                emailService.getEmailTemplate(newUserEmailRecipient.getEmailTemplateID(), user));

        repository.save(userEmailRecipientToAdd);

        return userEmailRecipientToAdd;
    }

    @Override
    public List<EmailRecipient> addRecipientsToUser(List<EmailRecipientDTO> newRecipientList, User user) {
        List<EmailRecipient> userRecipientListToAdd = new ArrayList<>();
        for(EmailRecipientDTO emailRecipient : newRecipientList){
            userService.checkAuthority(emailRecipient.getOwnerId(), user);
            EmailRecipient userEmailRecipientToAdd = new EmailRecipient(emailRecipient,
                    userService.getUserProfileById(emailRecipient.getOwnerId()),
                    emailService.getEmailTemplate(emailRecipient.getEmailTemplateID(), user));
            userRecipientListToAdd.add(userEmailRecipientToAdd);
            repository.save(userEmailRecipientToAdd);
        }

        return userRecipientListToAdd;

    }

    @Override
    public void deleteUserEmailRecipient(Long emailRecipientId, User user) {
        userService.checkAuthority(this.repository.getEmailRecipientOwnerById(emailRecipientId), user);
        repository.deleteEmailRecipientById(emailRecipientId);
    }


    @Override
    public EmailRecipient updateUserEmailRecipient(Long emailRecipientId, UpdateEmailRecipientDTO updatedEmailRecipient, User user) throws RecipientNotFoundException {
        EmailRecipient userRecipientToUpdate = this.getEmailRecipient(emailRecipientId, user);
        if(userRecipientToUpdate == null) {throw new RecipientNotFoundException();}

        userRecipientToUpdate.updateEmailRecipient(updatedEmailRecipient);

        repository.save(userRecipientToUpdate);
        return userRecipientToUpdate;
    }
}

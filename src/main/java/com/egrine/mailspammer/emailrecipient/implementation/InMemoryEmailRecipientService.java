package com.egrine.mailspammer.emailrecipient.implementation;

import com.egrine.mailspammer.email.EmailService;
import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import com.egrine.mailspammer.emailrecipient.EmailRecipientRepository;
import com.egrine.mailspammer.emailrecipient.EmailRecipientService;
import com.egrine.mailspammer.user.UserProfile;
import com.egrine.mailspammer.user.UserService;
import com.egrine.mailspammer.utility.Exceptions.ForbiddenOperationException;
import com.egrine.mailspammer.utility.Exceptions.RecipientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailRecipientService implements EmailRecipientService {
    EmailRecipientRepository repository;
    UserService userService;
    EmailService emailService;

    @Override
    public List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds) {
        List<EmailRecipient> recipients = repository.getEmailRecipientsByIdIn(recipientIds);
        return recipients == null ? Collections.emptyList() : recipients;
    }

    @Override
    public List<EmailRecipient> getAllUserRecipients(Long userId, User user) {
        userService.checkAuthority(userId, user);
        UserProfile owner = userService.getUserProfileById(userId);

        List<EmailRecipient> recipients = owner.getEmailRecipients();
        return recipients == null ? Collections.emptyList() : recipients;
    }

    @Override
    public EmailRecipient getEmailRecipient(Long emailRecipientId, User user) {
        this.checkAuthority(user, emailRecipientId);
        return repository.getEmailRecipientById(emailRecipientId);
    }

    @Override
    public List<UserProfile> getEmailRecipientOwnersById(Long emailRecipientId) {
        EmailRecipient recipient = repository.getEmailRecipientById(emailRecipientId);
        return recipient.getOwners();
    }

    @Override
    public EmailRecipient addUserEmailRecipient(EmailRecipientDTO newUserEmailRecipient, User user, Long userId) {
        userService.checkAuthority(userId, user); //  check that the authenticated user is allowed to perform the operation

        EmailRecipient userEmailRecipientToAdd = new EmailRecipient(newUserEmailRecipient,
                userService.getUserProfileById(newUserEmailRecipient.getOwnerId()),
                emailService.getEmailTemplate(newUserEmailRecipient.getEmailTemplateID(), user));

        repository.save(userEmailRecipientToAdd);

        return userEmailRecipientToAdd;
    }

    @Transactional
    @Override
    public List<EmailRecipient> addRecipientsToUser(List<EmailRecipientDTO> newRecipientList, User user, Long userId) {
        userService.checkAuthority(userId, user);

        List<EmailRecipient> userRecipientListToAdd = new ArrayList<>();

        for(EmailRecipientDTO emailRecipient : newRecipientList){

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
        this.checkAuthority(user, emailRecipientId);

        UserProfile owner = userService.getUserProfileByEmail(user.getUsername());

        EmailRecipient recipient = repository.getEmailRecipientById(emailRecipientId);

        recipient.getOwners().remove(owner);

        repository.save(recipient);

    }


    @Override
    public EmailRecipient updateUserEmailRecipient(Long emailRecipientId, UpdateEmailRecipientDTO updatedEmailRecipient, User user) throws RecipientNotFoundException {
        EmailRecipient userRecipientToUpdate = this.getEmailRecipient(emailRecipientId, user);
        if(userRecipientToUpdate == null) {throw new RecipientNotFoundException();}

        userRecipientToUpdate.updateEmailRecipient(updatedEmailRecipient);

        repository.save(userRecipientToUpdate);
        return userRecipientToUpdate;
    }

    @Override
    public void checkAuthority(User user, Long recipientId) {
        UserProfile currentUser = userService.getUserProfileByEmail(user.getUsername());
        List<UserProfile> recipientOwners = this.getEmailRecipientOwnersById(recipientId);


        for (UserProfile profile : recipientOwners){
            if (Objects.equals(profile.getId(), currentUser.getId())){
                return;
            }
        }

        // user not in the list of the recipient owners
        throw new ForbiddenOperationException();
    }
}

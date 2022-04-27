package com.egrine.mailspammer.email.implementation;
import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.email.EmailRepository;
import com.egrine.mailspammer.email.EmailService;
import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.emailrecipient.EmailRecipientRepository;
import com.egrine.mailspammer.user.UserProfile;
import com.egrine.mailspammer.user.UserService;
import com.egrine.mailspammer.utility.Exceptions.TemplateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailService implements EmailService {

    private final EmailRepository emailRepository;
    private final UserService userService;
    private final EmailRecipientRepository recipientRepository;

    /*
    todo
    * start writing some tests
    todo
   */

    @Override
    public List<EmailTemplate> getAllUserEmailTemplates(User user) {
        // get user profile from db
        UserProfile currenUser = userService.getUserProfileByEmail(user.getUsername());

        List<EmailTemplate> allUserEmailTemplates = emailRepository.getAllByEmailTemplateOwnerId(currenUser.getId());
        return allUserEmailTemplates==null ? Collections.emptyList() : allUserEmailTemplates;// return an empty list
    }

    @Override
    public EmailTemplate getEmailTemplate(Long emailTemplateId, User user) {
        userService.checkAuthority(this.getTemplateOwnerIdByTemplateId(emailTemplateId), user);
        return emailRepository.getEmailTemplateById(emailTemplateId);
    }

    @Override
    public EmailTemplate addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate, User user) {
        userService.checkAuthority(newUserEmailTemplate.getTemplateOwnerId(), user);

        EmailTemplate userEmailTemplateToAdd = new EmailTemplate(newUserEmailTemplate,
                userService.getUserProfileById(newUserEmailTemplate.getTemplateOwnerId()),
                recipientRepository.getEmailRecipientsByIdIn(newUserEmailTemplate.getEmailRecipients()));

        emailRepository.save(userEmailTemplateToAdd);

        return userEmailTemplateToAdd;
    }

    @Override
    public EmailTemplate updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate, User user) throws TemplateNotFoundException{
        EmailTemplate userTemplateToUpdate = this.getEmailTemplate(emailTemplateId, user);
        if(userTemplateToUpdate == null) {throw new TemplateNotFoundException();}

        userTemplateToUpdate.updateEmailTemplate(updatedEmailTemplate,
                recipientRepository.getEmailRecipientsByIdIn(updatedEmailTemplate.getEmailRecipients()));

        emailRepository.save(userTemplateToUpdate);
        return userTemplateToUpdate;
    }

    @Override
    public void deleteUserEmailTemplate(Long emailTemplateId, User user) {
        userService.checkAuthority(this.getTemplateOwnerIdByTemplateId(emailTemplateId), user);
        emailRepository.deleteEmailTemplateById(emailTemplateId);
    }

    @Override
    public Long getTemplateOwnerIdByTemplateId(Long emailTemplateId) {
        EmailTemplate template = emailRepository.getEmailTemplateById(emailTemplateId);

        return template.getEmailTemplateOwner().getId();
    }


}

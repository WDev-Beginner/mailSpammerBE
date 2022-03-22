package com.egrine.mailspammer.email.implementation;
import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.email.EmailRepository;
import com.egrine.mailspammer.email.EmailService;
import com.egrine.mailspammer.email.EmailTemplate;
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

    /*
    todo
    * start writing some tests
    todo
   */

    @Override
    public List<EmailTemplate> getAllUserEmailTemplates(Long emailTemplateOwnerId, User user) {
        userService.checkAuthority(emailTemplateOwnerId, user);
        List<EmailTemplate> allUserEmailTemplates = emailRepository.getAllByEmailTemplateOwnerId(emailTemplateOwnerId);
        return allUserEmailTemplates==null ? Collections.emptyList() : allUserEmailTemplates;// return an empty list
    }

    @Override
    public EmailTemplate getEmailTemplate(Long emailTemplateId, User user) {
        // need a function to return the user id based on the template id
        userService.checkAuthority(this.getTemplateOwnerByTemplateId(emailTemplateId), user);
        return emailRepository.getEmailTemplateById(emailTemplateId);
    }

    @Override
    public void addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate, User user) {
        userService.checkAuthority(newUserEmailTemplate.getTemplateOwner().getId(), user);
        EmailTemplate userEmailTemplateToAdd = new EmailTemplate(newUserEmailTemplate);// new template constructed by DTO
        emailRepository.save(userEmailTemplateToAdd);
    }

    @Override
    public void updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate, User user) throws TemplateNotFoundException{
        EmailTemplate userTemplateToUpdate = this.getEmailTemplate(emailTemplateId, user);
        if(userTemplateToUpdate == null) {throw new TemplateNotFoundException();}
        userTemplateToUpdate.updateEmailTemplate(updatedEmailTemplate);
        emailRepository.save(userTemplateToUpdate);
    }

    @Override
    public void deleteUserEmailTemplate(Long emailTemplateId, User user) {
        userService.checkAuthority(this.getTemplateOwnerByTemplateId(emailTemplateId), user);
        emailRepository.deleteEmailTemplateById(emailTemplateId);
    }

    @Override
    public Long getTemplateOwnerByTemplateId(Long emailTemplateId) {
        return emailRepository.getEmailTemplateOwnerById(emailTemplateId);
    }


}

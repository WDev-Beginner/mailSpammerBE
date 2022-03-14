package com.egrine.mailSpammer.email.implementation;
import com.egrine.mailSpammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailSpammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailSpammer.email.EmailRepository;
import com.egrine.mailSpammer.email.EmailService;
import com.egrine.mailSpammer.email.EmailTemplate;
import com.egrine.mailSpammer.utilityPackages.customExceptions.TemplateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailService implements EmailService {
    private final EmailRepository repository;

    /*
    todo
    * start writing some tests
    todo
   */

    @Override
    public List<EmailTemplate> getAllUserEmailTemplates(Long emailTemplateOwnerId) {
        List<EmailTemplate> allUserEmailTemplates = repository.getAllByEmailTemplateOwnerId(emailTemplateOwnerId);
        return allUserEmailTemplates==null ? Collections.emptyList() : allUserEmailTemplates;// return an empty list
    }

    @Override
    public EmailTemplate getEmailTemplate(Long emailTemplateId) {
        return repository.getEmailTemplateById(emailTemplateId);
    }

    @Override
    public void addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate) {
        EmailTemplate userEmailTemplateToAdd = new EmailTemplate(newUserEmailTemplate);// new template constructed by DTO
        repository.save(userEmailTemplateToAdd);
    }

    @Override
    public void updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate) throws TemplateNotFoundException{
        EmailTemplate userTemplateToUpdate = this.getEmailTemplate(emailTemplateId);
        if(userTemplateToUpdate == null) {throw new TemplateNotFoundException();}
        userTemplateToUpdate.updateEmailTemplate(updatedEmailTemplate);
        repository.save(userTemplateToUpdate);
    }

    @Override
    public void deleteUserEmailTemplate(Long emailTemplateId) {
        repository.deleteEmailTemplateById(emailTemplateId);
    }
}

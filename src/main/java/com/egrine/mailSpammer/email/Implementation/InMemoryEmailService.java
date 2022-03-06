package com.egrine.mailSpammer.email.Implementation;
import com.egrine.mailSpammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailSpammer.email.EmailRepository;
import com.egrine.mailSpammer.email.EmailService;
import com.egrine.mailSpammer.email.EmailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
class InMemoryEmailService implements EmailService {

    private final EmailRepository repository;

    @Override
    public List<EmailTemplate> getAllUserEmailTemplates(Long emailTemplateOwnerId) {
        List<EmailTemplate> allUserEmailTemplates = repository.getAllByEmailTemplateOwnerId(emailTemplateOwnerId);
        return allUserEmailTemplates==null ? Collections.emptyList(): allUserEmailTemplates;
    }

    @Override
    public EmailTemplate getEmailTemplate(Long emailTemplateId) {
        EmailTemplate userEmailTemplate = repository.getEmailTemplateById(emailTemplateId);
        return userEmailTemplate==null ? new EmailTemplate(): userEmailTemplate;
    }

    @Override
    public void addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate) {
        EmailTemplate userEmailTemplateToAdd = new EmailTemplate(newUserEmailTemplate);// new template constructed by DTO
        repository.save(userEmailTemplateToAdd);
    }

    @Override
    public void updateUserEmailTemplate(Long emailTemplateId, EmailTemplateDTO updatedEmailTemplate) {
        // get the email template
        EmailTemplate userTemplateToUpdate = this.getEmailTemplate(emailTemplateId);
        userTemplateToUpdate.setHtmlEmail(updatedEmailTemplate.getHtmlEmail());
        userTemplateToUpdate.setJsonEmail(updatedEmailTemplate.getJsonEmail());
        userTemplateToUpdate.setEmailRecipients(updatedEmailTemplate.getEmailRecipients());
        repository.save(userTemplateToUpdate);
    }

    @Override
    public void deleteUserEmailTemplate(Long emailTemplateId) {
        repository.deleteEmailTemplateById(emailTemplateId);
    }
}

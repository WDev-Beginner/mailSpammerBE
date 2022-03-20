package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;

import java.util.List;


public interface EmailService {

    List<EmailTemplate> getAllUserEmailTemplates(Long emailTemplateOwnerId);

    EmailTemplate getEmailTemplate(Long emailTemplateId);

    void addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate);

    void updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate);

    void deleteUserEmailTemplate(Long emailTemplateId);
}

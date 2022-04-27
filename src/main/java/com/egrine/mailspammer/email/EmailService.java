package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import org.springframework.security.core.userdetails.User;

import java.util.List;


public interface EmailService {

    List<EmailTemplate> getAllUserEmailTemplates(User user);

    EmailTemplate getEmailTemplate(Long emailTemplateId, User user);

    EmailTemplate addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate, User user);

    EmailTemplate updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate, User user);

    void deleteUserEmailTemplate(Long emailTemplateId, User user);

    Long getTemplateOwnerIdByTemplateId(Long emailTemplateId);

}


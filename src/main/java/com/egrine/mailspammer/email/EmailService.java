package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.user.UserProfile;
import org.springframework.security.core.userdetails.User;

import java.util.List;


public interface EmailService {

    List<EmailTemplate> getAllUserEmailTemplates(Long emailTemplateOwnerId, User user);

    EmailTemplate getEmailTemplate(Long emailTemplateId, User user);

    void addUserEmailTemplate(EmailTemplateDTO newUserEmailTemplate, User user);

    void updateUserEmailTemplate(Long emailTemplateId, UpdateEmailTemplateDTO updatedEmailTemplate, User user);

    void deleteUserEmailTemplate(Long emailTemplateId, User user);

    Long getTemplateOwnerByTemplateId(Long emailTemplateId);

}

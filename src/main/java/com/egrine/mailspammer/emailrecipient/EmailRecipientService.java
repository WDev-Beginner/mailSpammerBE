package com.egrine.mailspammer.emailrecipient;

import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface EmailRecipientService {
    List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds);

    List<EmailRecipient> getAllUserRecipients(Long userId, User user);

    EmailRecipient getEmailRecipient(Long emailRecipientId, User user);

    EmailRecipient addUserEmailRecipient(EmailRecipientDTO newUserEmailRecipient, User user);

    List<EmailRecipient> addRecipientsToUser(List<EmailRecipientDTO> newRecipientList, User user);

    void deleteUserEmailRecipient(Long emailRecipientId, User user);

    Long getEmailRecipientOwnerById(Long emailRecipientId);

    EmailRecipient updateUserEmailRecipient(Long emailRecipientId, UpdateEmailRecipientDTO updatedEmailRecipient, User user);
}

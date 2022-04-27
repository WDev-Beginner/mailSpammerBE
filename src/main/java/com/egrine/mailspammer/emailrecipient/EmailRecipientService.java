package com.egrine.mailspammer.emailrecipient;

import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import com.egrine.mailspammer.user.UserProfile;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface EmailRecipientService {
    List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds);

    List<EmailRecipient> getAllUserRecipients(Long userId, User user);

    EmailRecipient getEmailRecipient(Long emailRecipientId, User user);

    EmailRecipient addUserEmailRecipient(EmailRecipientDTO newUserEmailRecipient, User user, Long userId);

    List<EmailRecipient> addRecipientsToUser(List<EmailRecipientDTO> newRecipientList, User user, Long userId);

    void deleteUserEmailRecipient(Long emailRecipientId, User user);

    List<UserProfile> getEmailRecipientOwnersById(Long emailRecipientId);

    EmailRecipient updateUserEmailRecipient(Long emailRecipientId, UpdateEmailRecipientDTO updatedEmailRecipient, User user);

    void checkAuthority(User user, Long recipientId);
}

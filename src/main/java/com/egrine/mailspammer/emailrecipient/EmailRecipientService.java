package com.egrine.mailspammer.emailrecipient;

import java.util.List;

public interface EmailRecipientService {
    List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds);
}

package com.egrine.mailspammer.emailrecipient.implementation;

import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import com.egrine.mailspammer.emailrecipient.EmailRecipientRepository;
import com.egrine.mailspammer.emailrecipient.EmailRecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailRecipientService implements EmailRecipientService {
    EmailRecipientRepository repository;

    @Override
    public List<EmailRecipient> getEmailRecipientsById(List<Long> recipientIds) {
        List<EmailRecipient> recipients = repository.getEmailRecipientsById(recipientIds);
        return recipients == null ? Collections.emptyList() : recipients;
    }
}

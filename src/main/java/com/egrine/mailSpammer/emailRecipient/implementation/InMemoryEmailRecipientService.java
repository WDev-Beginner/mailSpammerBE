package com.egrine.mailSpammer.emailRecipient.implementation;

import com.egrine.mailSpammer.emailRecipient.EmailRecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailRecipientService implements EmailRecipientService {
}

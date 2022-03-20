package com.egrine.mailspammer.emailrecipient.implementation;

import com.egrine.mailspammer.emailrecipient.EmailRecipientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryEmailRecipientService implements EmailRecipientService {
}

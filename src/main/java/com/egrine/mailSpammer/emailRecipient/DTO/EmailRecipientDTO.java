package com.egrine.mailSpammer.emailRecipient.DTO;

import com.egrine.mailSpammer.email.EmailTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailRecipientDTO {
    private final String emailAddress;
    private final boolean isActive;
    private final EmailTemplate newAssignedEmailTemplate;
}

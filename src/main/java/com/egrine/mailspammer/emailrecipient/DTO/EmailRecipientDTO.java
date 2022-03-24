package com.egrine.mailspammer.emailrecipient.DTO;

import com.egrine.mailspammer.email.EmailTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailRecipientDTO {
    private final String emailAddress;
    private final boolean isActive;
    private final EmailTemplate newAssignedEmailTemplate; // DTO should get id instead of entity
}

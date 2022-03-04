package com.egrine.mailSpammer.email.DTO;

import com.egrine.mailSpammer.email.EmailTemplate;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class EmailRecipientDTO {
    private final String emailAddress;
    private final boolean isActive;
    private final EmailTemplate newAssignedEmailTemplate;
}

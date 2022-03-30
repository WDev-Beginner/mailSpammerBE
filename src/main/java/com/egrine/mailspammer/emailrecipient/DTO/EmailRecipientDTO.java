package com.egrine.mailspammer.emailrecipient.DTO;

import com.egrine.mailspammer.email.EmailTemplate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class EmailRecipientDTO {
    private final String emailAddress;
    private final Long ownerId;
    private final Long emailTemplateID; // DTO should get id instead of entity
}

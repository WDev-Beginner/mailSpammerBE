package com.egrine.mailspammer.emailrecipient.DTO;

import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.user.UserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UpdateEmailRecipientDTO {
    private final String emailAddress;
    private final boolean isActive;
    private final List<UserProfile> owners;
    private final List<EmailTemplate> templates;
}
package com.egrine.mailspammer.email.DTO;

import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import com.egrine.mailspammer.user.UserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class EmailTemplateDTO {
    private final String htmlEmail;
    private final JSONObject jsonEmail;
    private final Long templateOwnerId;
    private final List<Long> emailRecipients;
}

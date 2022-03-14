package com.egrine.mailSpammer.email.DTO;

import com.egrine.mailSpammer.emailRecipient.EmailRecipient;
import com.egrine.mailSpammer.user.UserProfile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class EmailTemplateDTO {
    private final String htmlEmail;
    private final JSONObject jsonEmail;
    private final UserProfile templateOwner;
    private final List<EmailRecipient> emailRecipients;
}

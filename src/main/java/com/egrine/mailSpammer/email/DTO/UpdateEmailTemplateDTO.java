package com.egrine.mailSpammer.email.DTO;

import com.egrine.mailSpammer.emailRecipient.EmailRecipient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import java.util.List;


@Getter
@RequiredArgsConstructor
public class UpdateEmailTemplateDTO {
    private final String htmlEmail;
    private final JSONObject jsonEmail;
    private final List<EmailRecipient> emailRecipients;
}

package com.egrine.mailspammer.email.DTO;

import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import java.util.List;


@Getter
@RequiredArgsConstructor
public class UpdateEmailTemplateDTO {
    private final String htmlEmail;
    private final JSONObject jsonEmail;
    private final List<Long> emailRecipients;
}

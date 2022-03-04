package com.egrine.mailSpammer.email.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

@RequiredArgsConstructor
@Getter
public class EmailTemplateDTO {
    private final String htmlEmail;
    private final JSONObject jsonEmail;
}

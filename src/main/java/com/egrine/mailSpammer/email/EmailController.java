package com.egrine.mailSpammer.email;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService service;
}

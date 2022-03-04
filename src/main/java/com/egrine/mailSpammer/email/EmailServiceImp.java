package com.egrine.mailSpammer.email;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
public class EmailServiceImp implements EmailService{

    private final EmailRepository repository;

}

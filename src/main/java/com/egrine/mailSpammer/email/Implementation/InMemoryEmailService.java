package com.egrine.mailSpammer.email.Implementation;
import com.egrine.mailSpammer.email.EmailRepository;
import com.egrine.mailSpammer.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
class InMemoryEmailService implements EmailService {

    private final EmailRepository repository;

}

package com.egrine.mailspammer.email;

import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplate, Long> {


    List<EmailTemplate> getAllByEmailTemplateOwnerId(Long emailTemplateOwnerId);

    EmailTemplate getEmailTemplateById(Long emailTemplateId);

    void deleteEmailTemplateById(Long emailTemplateId);




}


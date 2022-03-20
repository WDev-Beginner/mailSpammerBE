package com.egrine.mailspammer.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<EmailTemplate, Long> {


    List<EmailTemplate> getAllByEmailTemplateOwnerId(Long emailTemplateOwnerId);

    EmailTemplate getEmailTemplateById(Long emailTemplateId);

    void deleteEmailTemplateById(Long emailTemplateId);
}

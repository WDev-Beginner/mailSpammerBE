package com.egrine.mailspammer.emailrecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {

    @Query(value="SELECT email_recipient.id, email_recipient.email_address, email_recipient.is_active" +
            "FROM email_recipient " +
            "WHERE email_template.id IN ?1;",
            nativeQuery = true)
    List<EmailRecipient> getEmailRecipientsById(List<Long> Ids);
}
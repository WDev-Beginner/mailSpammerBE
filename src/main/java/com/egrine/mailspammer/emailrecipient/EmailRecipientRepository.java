package com.egrine.mailspammer.emailrecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {

    List<EmailRecipient> getEmailRecipientsByIdIn(List<Long> Ids);


    EmailRecipient getEmailRecipientById(Long Id);
}

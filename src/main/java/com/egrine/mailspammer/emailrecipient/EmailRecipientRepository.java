package com.egrine.mailspammer.emailrecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRecipientRepository extends JpaRepository<EmailRecipient, Long> {
}

package com.egrine.mailspammer.emailrecipient;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.email.EmailTemplate;
import com.egrine.mailspammer.emailrecipient.DTO.EmailRecipientDTO;
import com.egrine.mailspammer.emailrecipient.DTO.UpdateEmailRecipientDTO;
import com.egrine.mailspammer.user.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class EmailRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String emailAddress;

    @Column
    private boolean isActive;

    @ManyToMany
    private List<UserProfile> owners;

    @ManyToMany
    private List<EmailTemplate> emailTemplates;

    // custom DTO constructor
    public EmailRecipient(EmailRecipientDTO newEmailRecipient, UserProfile userProfile, EmailTemplate emailTemplate){
        this.setEmailAddress(newEmailRecipient.getEmailAddress());
        this.owners.add(userProfile);
        this.emailTemplates.add(emailTemplate);
        this.setActive(true);
    }

    // custom update function
    public void updateEmailRecipient(UpdateEmailRecipientDTO updatedEmailRecipient){
        this.setEmailAddress(updatedEmailRecipient.getEmailAddress());
        this.setActive(updatedEmailRecipient.isActive());
        this.setOwners(updatedEmailRecipient.getOwners());
        this.setEmailTemplates(updatedEmailRecipient.getTemplates());

    }
}

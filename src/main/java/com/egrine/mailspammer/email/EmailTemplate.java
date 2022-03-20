package com.egrine.mailspammer.email;

import com.egrine.mailspammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailspammer.email.DTO.UpdateEmailTemplateDTO;
import com.egrine.mailspammer.emailrecipient.EmailRecipient;
import com.egrine.mailspammer.user.UserProfile;
import com.egrine.mailspammer.utility.dbconverters.JsonToStringConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "TEXT") // think about using a document database
    private String htmlEmail;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonToStringConverter.class)
    private JSONObject jsonEmail;

    @ManyToOne
    @JoinColumn
    private UserProfile emailTemplateOwner;

    @ManyToMany(mappedBy = "emailTemplates")
    private List<EmailRecipient> emailRecipients;

    // custom DTO constructor
    public EmailTemplate(EmailTemplateDTO newEmailTemplate) {
        this.setHtmlEmail(newEmailTemplate.getHtmlEmail());
        this.setJsonEmail(newEmailTemplate.getJsonEmail());
        this.setEmailTemplateOwner(newEmailTemplate.getTemplateOwner());
        this.setEmailRecipients(newEmailTemplate.getEmailRecipients());
    }

    // custom update function
    public void updateEmailTemplate(UpdateEmailTemplateDTO updatedEmailTemplate) {
        this.setHtmlEmail(updatedEmailTemplate.getHtmlEmail());
        this.setJsonEmail(updatedEmailTemplate.getJsonEmail());
        this.setEmailRecipients(updatedEmailTemplate.getEmailRecipients());
    }
}

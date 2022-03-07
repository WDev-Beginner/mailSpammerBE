package com.egrine.mailSpammer.email;

import com.egrine.mailSpammer.email.DTO.EmailTemplateDTO;
import com.egrine.mailSpammer.user.UserProfile;
import com.egrine.mailSpammer.utilities.JsonToStringConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String htmlEmail;

    @Column(columnDefinition = "TEXT")
    @Convert(converter = JsonToStringConverter.class)
    private JSONObject jsonEmail;

    @ManyToOne
    @JoinColumn()
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

    public String getHtmlEmail() {
        return htmlEmail;
    }

    public void setHtmlEmail(String htmlEmail) {
        this.htmlEmail = htmlEmail;
    }

    public JSONObject getJsonEmail() {
        return jsonEmail;
    }

    public void setJsonEmail(JSONObject jsonEmail) {
        this.jsonEmail = jsonEmail;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<EmailRecipient> getRecipients() {
        return emailRecipients;
    }

    public void setRecipients(List<EmailRecipient> recipients) {
        this.emailRecipients = recipients;
    }

    public void setEmailTemplateOwner(UserProfile emailTemplateOwner) {
        this.emailTemplateOwner = emailTemplateOwner;
    }

    public void setEmailRecipients(List<EmailRecipient> emailRecipients) {
        this.emailRecipients = emailRecipients;

    }

}

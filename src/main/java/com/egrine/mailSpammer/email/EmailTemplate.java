package com.egrine.mailSpammer.email;

import com.egrine.mailSpammer.utilities.JsonToStringConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import javax.persistence.*;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String htmlEmail;

    @Column
    @Convert(converter = JsonToStringConverter.class)
    private JSONObject jsonEmail;

    @Column
    @OneToMany
    private List<EmailRecipient> recipients;

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
        return recipients;
    }

    public void setRecipients(List<EmailRecipient> recipients) {
        this.recipients = recipients;
    }


}

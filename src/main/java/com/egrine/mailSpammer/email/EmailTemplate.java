package com.egrine.mailSpammer.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

    // this will store the html version of the email Template
    @Column
    private String content;

    // todo -> add a field to hold the json version

    // attachments are no longer needed as the html version is ready to send
    @Column
    @Convert(converter = ListToStringConverter.class)
    private List<String> attachments;

    @Column
    @OneToMany
    private List<EmailRecipient> recipients;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<String> attachments) {
        this.attachments = attachments;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }
}

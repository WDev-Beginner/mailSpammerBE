package com.egrine.mailSpammer.emailRecipient;

import com.egrine.mailSpammer.email.EmailTemplate;
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
    private List<EmailTemplate> emailTemplates;
}

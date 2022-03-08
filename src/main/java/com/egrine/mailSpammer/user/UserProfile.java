package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.email.EmailRecipient;
import com.egrine.mailSpammer.email.EmailTemplate;
import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    private String emailAddress;
    @Column
    private String password;
    @OneToMany(mappedBy = "emailTemplateOwner")
    private List<EmailTemplate> emailTemplates;
    @Column
    private boolean accountStatus; // true for active / false for deleted

    // custom DTO constructor
    public UserProfile(UserProfileDTO user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmailAddress(user.getEmailAddress());
        this.setPassword(user.getPassword()); // password is sent hashed from the front end
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(boolean accountStatus) {
        this.accountStatus = accountStatus;
    }


}

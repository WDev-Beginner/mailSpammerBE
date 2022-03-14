package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
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
    @Column
    private boolean isAccountActive; // true for active / false for deleted

    // custom secure/normal DTO constructor
    public UserProfile(String password, UserProfileDTO user) {
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmailAddress(user.getEmailAddress());
        this.setPassword(password);
    }
}

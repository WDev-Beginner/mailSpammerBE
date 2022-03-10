package com.egrine.mailSpammer.user.DTO;
import lombok.Getter;



@Getter
public class SecureUserProfileDTO {
    private final Long Id;
    private final String firstName;
    private final String lastName;
    private final String emailAddress;

    public SecureUserProfileDTO(Long Id, UserProfileDTO newUser){
        this.Id = Id;
        this.firstName = newUser.getFirstName();
        this.lastName = newUser.getLastName();
        this.emailAddress = newUser.getEmailAddress();
    }
}

package com.egrine.mailSpammer.user.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserProfileDTO {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String password;
}

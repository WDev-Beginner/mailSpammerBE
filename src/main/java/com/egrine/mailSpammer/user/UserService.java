package com.egrine.mailSpammer.user;

import com.egrine.mailSpammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailSpammer.user.DTO.UserProfileDTO;

public interface UserService {

    SecureUserProfileDTO addUser(UserProfileDTO newUser);

    void deleteUser(Long UserId);

    UserProfile getUserProfileById(Long userId);

    UserProfile getUserProfileByEmail(String emailAddress);

}

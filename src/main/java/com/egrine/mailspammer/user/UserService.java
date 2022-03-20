package com.egrine.mailspammer.user;

import com.egrine.mailspammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailspammer.user.DTO.UserProfileDTO;

public interface UserService {

    SecureUserProfileDTO addUser(UserProfileDTO newUser);

    void deleteUser(Long UserId);

    UserProfile getUserProfileById(Long userId);

    UserProfile getUserProfileByEmail(String emailAddress);

}

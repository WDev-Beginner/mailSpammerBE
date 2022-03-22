package com.egrine.mailspammer.user;

import com.egrine.mailspammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailspammer.user.DTO.UserProfileDTO;
import org.springframework.security.core.userdetails.User;

public interface UserService {

    SecureUserProfileDTO addUser(UserProfileDTO newUser);

    void deleteUser(Long UserId, User authenticatedUser);

    UserProfile getUserProfileById(Long userId);

    UserProfile getUserProfileByEmail(String emailAddress);

    void checkAuthority(Long Id, User user);

}

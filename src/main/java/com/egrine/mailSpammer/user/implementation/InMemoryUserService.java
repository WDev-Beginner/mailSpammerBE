package com.egrine.mailSpammer.user.implementation;
import com.egrine.mailSpammer.user.DTO.SecureUserProfileDTO;
import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import com.egrine.mailSpammer.user.UserProfile;
import com.egrine.mailSpammer.user.UserRepository;
import com.egrine.mailSpammer.user.UserService;
import com.egrine.mailSpammer.utilityPackages.customExceptions.UserAlreadyExistException;
import com.egrine.mailSpammer.utilityPackages.customExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryUserService implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /*
   todo
   * start writing some tests
   todo
  */

    @Override
    public SecureUserProfileDTO addUser(UserProfileDTO newProfileDTO) {
        /*
        the function checks if the user with provided email exists,
        if yes the function throws a custom exception, else it adds the user
        to the database
        */
        UserProfile loadedUserProfile = this.getUserProfileByEmail(newProfileDTO.getEmailAddress());
        if (loadedUserProfile != null) { throw new UserAlreadyExistException(); }

        String encryptedPassword =  passwordEncoder.encode(newProfileDTO.getPassword());

        UserProfile newUserProfile = new UserProfile(encryptedPassword, newProfileDTO);
        newUserProfile.setIsAccountActive(true);
        repository.save(newUserProfile);

        return new SecureUserProfileDTO(newUserProfile.getId(), newProfileDTO);
    }

    @Override
    public void deleteUser(Long userId) {
        /*
        the function does not actually delete the user
        from the database but only deactivates them(accountStatus -> false, email -> ****************)
        */
        UserProfile userProfileToDelete = this.getUserProfileById(userId);
        if (userProfileToDelete == null) { throw new UserNotFoundException(); }

        userProfileToDelete.setIsAccountActive(false);
        userProfileToDelete.setEmailAddress("************************");
        repository.save(userProfileToDelete);
    }

    @Override
    public UserProfile getUserProfileById(Long userId) {
        /*
        the function returns null if the user with given id does not exist or the
        provided user id belongs to a deleted user
        */
        UserProfile fetchedUser = repository.getUserProfileById(userId);
        return (fetchedUser == null || !fetchedUser.getIsAccountActive()) ? null : fetchedUser;
    }

    @Override
    public UserProfile getUserProfileByEmail(String emailAddress) {
        /*
        the function returns null if the user with given email does not exist or the
        provided user email belongs to a deleted user
        */
        UserProfile fetchedUser = repository.getUserProfileByEmailAddress(emailAddress);
        return (fetchedUser == null || !fetchedUser.getIsAccountActive()) ? null : fetchedUser;
    }
}

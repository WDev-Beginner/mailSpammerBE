package com.egrine.mailSpammer.user.Implementation;
import com.egrine.mailSpammer.user.DTO.UserProfileDTO;
import com.egrine.mailSpammer.user.UserProfile;
import com.egrine.mailSpammer.user.UserRepository;
import com.egrine.mailSpammer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class InMemoryUserServiceImp implements UserService {
    private final UserRepository repository;

    @Override
    public UserProfile addUser(UserProfileDTO newUser) {
        UserProfile newUserProfile = new UserProfile(newUser);
        newUserProfile.setAccountStatus(true);// user account status is set here to true at creation
        repository.save(newUserProfile);
        return newUserProfile;
    }

    @Override
    public void deleteUser(Long userId) {
        /*
        the function does not actually delete the user
        from the database but only deactivates them
        */
        UserProfile userProfileToDelete = this.getUserProfileById(userId);
        userProfileToDelete.setAccountStatus(false);
        repository.save(userProfileToDelete);
    }

    @Override
    public UserProfile getUserProfileById(Long userId) {
        UserProfile fetchedUser = repository.getUserProfileById(userId);
        if(fetchedUser == null){        // user does not exist
            return null;
        }
        return fetchedUser.getAccountStatus() ? fetchedUser : null;     // cannot get a deleted user
    }

    @Override
    public UserProfile getUserProfileByEmail(String emailAddress) {
        UserProfile fetchedUser = repository.getUserProfileByEmailAddress(emailAddress);
        if(fetchedUser == null){
            return null;
        }
        return fetchedUser.getAccountStatus() ? fetchedUser : null;     // cannot get a deleted user
    }


}

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
    public void addUser(UserProfileDTO newUser) {
        UserProfile newUserProfile = new UserProfile(newUser);
        repository.save(newUserProfile);
    }
}

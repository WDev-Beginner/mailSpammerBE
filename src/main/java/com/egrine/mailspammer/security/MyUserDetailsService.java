package com.egrine.mailspammer.security;
import com.egrine.mailspammer.user.UserProfile;
import com.egrine.mailspammer.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyUserDetailsService implements UserDetailsService {
    /*
    Platform authentication is through email
    no special user authorities, all system users are normal users (for now)
     */

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userEmailAddress) throws UsernameNotFoundException {

        UserProfile authenticatedUser = repository.getUserProfileByEmailAddress(userEmailAddress);
        if (authenticatedUser == null) {
            throw new UsernameNotFoundException("User with given email does not exist!");
        }

        return new User(authenticatedUser.getEmailAddress(), authenticatedUser.getPassword(), Collections.emptyList());
    }


}

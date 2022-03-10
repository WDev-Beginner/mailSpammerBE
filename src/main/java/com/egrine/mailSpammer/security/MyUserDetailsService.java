package com.egrine.mailSpammer.security;
import com.egrine.mailSpammer.user.UserProfile;
import com.egrine.mailSpammer.user.UserRepository;
import com.egrine.mailSpammer.utilityPackages.customExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userEmailAddress) throws UserNotFoundException {

        UserProfile authenticatedUser = repository.getUserProfileByEmailAddress(userEmailAddress);
        if(authenticatedUser == null){throw new UserNotFoundException();}

        return new User(authenticatedUser.getEmailAddress(), authenticatedUser.getPassword(), Collections.emptyList());
    }


}

package com.egrine.mailspammer.user.implementation;

import com.egrine.mailspammer.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;


class InMemoryUserServiceTest {

    // testing addUser function
    @BeforeEach
    void initTest() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
    }



    @Test
    void shouldReturnExceptionWhenUserAlreadyExists(){


    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUserProfileById() {
    }

    @Test
    void getUserProfileByEmail() {
    }
}
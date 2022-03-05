package com.egrine.mailSpammer.user.Implementation;
import com.egrine.mailSpammer.user.UserRepository;
import com.egrine.mailSpammer.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class InMemoryUserServiceImp implements UserService {
    private final UserRepository repository;

}

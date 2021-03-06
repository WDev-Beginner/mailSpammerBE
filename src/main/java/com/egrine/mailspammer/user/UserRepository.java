package com.egrine.mailspammer.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile getUserProfileById(Long userId);

    UserProfile getUserProfileByEmailAddress(String emailAddress);

}

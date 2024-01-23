package com.tweetroo.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tweetroo.api.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByUsername(String username);
}

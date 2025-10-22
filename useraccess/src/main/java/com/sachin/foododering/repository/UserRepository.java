package com.sachin.foododering.repository;

import com.sachin.foododering.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    // New method to find users by role
    List<User> findByRole(String role);
}

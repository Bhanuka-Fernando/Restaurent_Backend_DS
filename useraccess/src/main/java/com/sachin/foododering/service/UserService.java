// === src/main/java/com/sachin/foododering/service/UserService.java ===
package com.sachin.foododering.service;

import com.sachin.foododering.model.User;
import com.sachin.foododering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Update registerUser to include email and mobileNumber
    public User registerUser(String username, String password, String role, String email, String mobileNumber) {
        // Validate the inputs if necessary (email, mobileNumber)

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Ensure the password is encoded
        user.setRole(role);
        user.setEmail(email);  // Set the email
        user.setMobileNumber(mobileNumber);  // Set the mobile number

        return userRepository.save(user); // Save and return the saved user
    }

    // Method to find user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to get all customers
    public List<User> getAllCustomers() {
        return userRepository.findByRole("CUSTOMER");
    }
}

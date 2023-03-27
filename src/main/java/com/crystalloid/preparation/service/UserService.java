package com.crystalloid.preparation.service;

import com.crystalloid.preparation.model.UserDetails;
import com.crystalloid.preparation.repository.UserRepository;
import com.google.inject.Inject;

public class UserService {

    @Inject
    private UserRepository userRepository;

    public UserDetails getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void save(UserDetails user) {
        userRepository.save(user);
    }
}

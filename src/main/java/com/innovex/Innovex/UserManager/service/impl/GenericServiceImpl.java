package com.innovex.Innovex.UserManager.service.impl;

import com.innovex.Innovex.UserManager.domain.User;
import com.innovex.Innovex.UserManager.repository.UserRepository;
import com.innovex.Innovex.UserManager.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class GenericServiceImpl implements GenericService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

}

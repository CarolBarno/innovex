package com.innovex.Innovex.UserManager.service;

import com.innovex.Innovex.UserManager.domain.User;

import java.util.List;

/**
 *
 */
public interface GenericService {

    User findByUsername(String username);

    List<User> findAllUsers();
}

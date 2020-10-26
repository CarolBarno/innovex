package com.innovex.Innovex.service;

import com.innovex.Innovex.domain.RandomCity;
import com.innovex.Innovex.domain.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

    List<RandomCity> findAllRandomCities();
}

package com.innovex.Innovex.UserManager.service.impl;

import com.innovex.Innovex.UserManager.domain.User;
import com.innovex.Innovex.UserManager.repository.UserRepository;
import com.innovex.Innovex.utilities.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("at line 27 validating phone number first character");

        String phoneNumber = userName.trim();
        char first = phoneNumber.charAt(0);
        if (String.valueOf(first).equals("0")) {
            int index = 0;
            String countryCode = "254";
            phoneNumber = phoneNumber.substring(0, index)
                    + countryCode
                    + phoneNumber.substring(index + 1);
        } else {
            phoneNumber = userName.trim();
        }
        User user = userRepository.findByUsernameOrEmail(phoneNumber, userName)
                .orElseThrow(()
                        -> new UsernameNotFoundException(String.format("The username %s doesn't exist", userName))
                );

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        return userDetails;
    }

    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<User> findByIdNumber(String idNumber) {
        return userRepository.findByPhoneNumber(idNumber);
    }

    public Optional<User> findByEmailIgnoreCase(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    public Optional<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> APIException.notFound("The user is not found"));
    }

    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}

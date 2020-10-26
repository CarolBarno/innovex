/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.controller;

import com.innovex.Innovex.UserManager.data.UserData;
import com.innovex.Innovex.UserManager.domain.Role;
import com.innovex.Innovex.UserManager.domain.User;
import com.innovex.Innovex.UserManager.service.RoleService;
import com.innovex.Innovex.UserManager.service.impl.AppUserDetailsService;
import com.innovex.Innovex.utilities.exception.APIException;
import com.innovex.Innovex.utilities.utility.APIResponse;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author
 */
@Api
@RestController
@Slf4j
@RequestMapping(value = "api")
public class UsersController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private RoleService roleService;

    @PostMapping("users")
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody UserData userData) {
        User user = UserData.map(userData);
        List<Role> roles = new ArrayList<>();
        for (String roleData : userData.getRoles()) {
            Role role = roleService.getRoleByName("Role_" + roleData)
                    .orElseThrow(
                            () -> APIException.notFound("No Role exisit with the name {0}", roleData)
                    );
            roles.add(role);
        }
        String userName = userData.getPhoneNumber();
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
        user.setPhoneNumber(phoneNumber);
        user.setUsername(phoneNumber);
        user.setRoles(roles);

        Optional<User> user1 = appUserDetailsService.findByPhoneNumber(phoneNumber);
        if (user1.isPresent()) {
            throw APIException.conflict("The phone number already exists", phoneNumber);
        }
        Optional<User> user2 = appUserDetailsService.findByIdNumber(userData.getIdNumber());
        if (user2.isPresent()) {
            throw APIException.conflict("The id number {0} already exists", userData.getIdNumber());
        }
        Optional<User> user3 = appUserDetailsService.findByEmailIgnoreCase(userData.getEmail());
        if (user3.isPresent()) {
            throw APIException.conflict("The email {0} already exists", userData.getEmail());
        }
        Optional<User> user5 = appUserDetailsService.findByUsernameOrEmail(userData.getUsername(), userData.getEmail());
        if (user5.isPresent()) {
            throw APIException.conflict("The user name {0} {0}  already exists", userData.getUsername(), userData.getEmail());
        }
        appUserDetailsService.saveUser(user);

        return ResponseEntity.ok("Registration Successful");
    }

    @GetMapping("users")
    public Optional<UserData> getProfile(Authentication authentication) {
        String userName = authentication.getName();
        System.out.println("name is..." + userName);
        Optional<UserData> user = appUserDetailsService.findByUsernameOrEmail(userName, userName).map((a) -> UserData.map(a));
        if (user.isPresent()) {
            return user;
        } else {
            throw APIException.notFound("User Profile Not Found");
        }
    }

    @PutMapping("users/{id}")
    public @ResponseBody
    ResponseEntity<?> updatingProfile(@PathVariable("id") final Long id, @RequestBody @Valid final UserData data, Authentication authentication) {
        String userName = authentication.getName();
        Optional<UserData> user = appUserDetailsService.findByUsernameOrEmail(userName, userName).map((a) -> UserData.map(a));
        if (!user.isPresent()) {
            throw APIException.notFound("The user identified by user name {0} does not exist", userName);
        }
        User updatedUser = appUserDetailsService.findUserById(user.get().getUserId());
        updatedUser.setPhoneNumber(data.getPhoneNumber());
        updatedUser.setEmail(data.getEmail());
        updatedUser.setPassword(data.getPassword());
        updatedUser.setFirstName(data.getFirstName());
        updatedUser.setLastName(data.getLastName());
        updatedUser.setSurName(data.getSurName());
        User savedUser = appUserDetailsService.updateUser(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(true, "User details updated successfully.", HttpStatus.OK.value(), savedUser));

    }
}

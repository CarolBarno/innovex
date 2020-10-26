/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.data;

import com.innovex.Innovex.UserManager.domain.User;
import com.innovex.Innovex.UserManager.domain.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author
 */
@Data
public class UserData {

    @ApiModelProperty(required = false, hidden = true)
    private Long userId;
    @ApiModelProperty(required = false, hidden = true)
    private Long uuid;

    @ApiModelProperty(required = false, hidden = true)
    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email(message = "Please Provide a valid email")
    private String email;
    // @ValidPassword
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private String password;

    @NotBlank
    @Size(min = 3, max = 40)
    private String firstName;
    private String lastName;
    private String surName;
    @Enumerated(EnumType.STRING)
    private UserTypeEnum userTypeEnum;
    private String phoneNumber;
    private String dob;
    private String idNumber;
    @ApiModelProperty(required = false, hidden = true)
    private LocalDateTime lastLogin;
    @ApiModelProperty(required = false, hidden = true)
    private boolean accountNonLocked;
    @ApiModelProperty(required = false, hidden = true)

    private boolean accountNonExpired;
    @ApiModelProperty(required = false, hidden = true)

    private boolean credentialsNonExpired;
    @ApiModelProperty(required = false, hidden = true)
    private boolean verified;
    @ApiModelProperty(required = false, hidden = true)
    private boolean OTP;

    @ApiModelProperty(required = false, hidden = true)
    private String fullNames;

    @ApiModelProperty(required = false, hidden = true)
    private List<String> roles = new ArrayList<>();

    public static UserData map(User user) {
        UserData userData = new UserData();
        userData.setUserTypeEnum(user.getUserTypeEnum());
        userData.setFullNames(user.getFirstName() + " " + user.getLastName() + " " + user.getSurName());
        userData.setIdNumber(user.getIdNumber());
        userData.setPhoneNumber(user.getPhoneNumber());
        userData.setEmail(user.getEmail());
        userData.setDob(user.getDob());
        userData.setUsername(user.getUsername());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setSurName(user.getSurName());

        return userData;
    }

    public static User map(UserData data) {
        User user = new User();
        user.setUserTypeEnum(data.getUserTypeEnum());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setSurName(data.getSurName());
        user.setIdNumber(data.getIdNumber());
        user.setPhoneNumber(data.getPhoneNumber());
        user.setEmail(data.getEmail());
        user.setDob(data.getDob());
        user.setUsername(data.getUsername());
        user.setPassword(data.getPassword());
        return user;
    }
}

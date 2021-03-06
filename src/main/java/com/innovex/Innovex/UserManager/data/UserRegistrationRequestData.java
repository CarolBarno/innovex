/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.data;

import com.innovex.Innovex.config.UserValidator.ValidCreateUser;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author
 */
@Data
@ValidCreateUser
public class UserRegistrationRequestData {

    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size(min = 3, max = 15)
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email(message = "Please Provide a valid email")
    private String email;

    //@ValidPassword
//    private String password;
    private List<String> roles = new ArrayList<>();

    private String phone;

    private String userType;

}

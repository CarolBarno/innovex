/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.config.PasswordValidator;

import lombok.Data;

/**
 *
 * @author
 */
@Data
public class PasswordDto {

    private String currentPassword;
    // @ValidPassword
    private String newPassword;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.config.UserValidator;

import com.innovex.Innovex.UserManager.data.UserRegistrationRequestData;
import com.innovex.Innovex.UserManager.domain.User;
import com.innovex.Innovex.UserManager.repository.UserRepository;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.Data;

/**
 *
 * @author
 */
@Data
public class CreateUserValidator implements ConstraintValidator<ValidCreateUser, UserRegistrationRequestData> {

    private final UserRepository userRepository;

    public CreateUserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UserRegistrationRequestData userRegistrationRequestData, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> user = userRepository.findByUsernameOrEmail(userRegistrationRequestData.getUsername(), userRegistrationRequestData.getEmail());
        if (user.isPresent()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("The user already exist, Please enter different username").addConstraintViolation();
            return false;
        }
        return true;
    }
}

package com.tamnt.spring.sample.validator;

import com.tamnt.spring.sample.model.User;
import com.tamnt.spring.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "userForm.notEmpty");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "userForm.username.size");
        }
        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "userForm.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "userForm.notEmpty");
        if (user.getPassword().length() < 4 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "userForm.password.size");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "userForm.passwordConfirm.validate");
        }
    }
}

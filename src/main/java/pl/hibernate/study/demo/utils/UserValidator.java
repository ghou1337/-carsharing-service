package pl.hibernate.study.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.exe.UserExist;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) throws RuntimeException {
        User user = (User) target;
        if (userService.findUserByLogin(user.getLogin()).isPresent()) {
            errors.rejectValue("login", "", "User already exist");
        }
    }
}

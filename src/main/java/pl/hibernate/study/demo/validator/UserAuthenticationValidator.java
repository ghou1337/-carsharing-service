package pl.hibernate.study.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.UserService;

@Component
public class UserAuthenticationValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserAuthenticationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) throws RuntimeException {
        User user = (User) target;
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "User already exist");
        }
    }
}

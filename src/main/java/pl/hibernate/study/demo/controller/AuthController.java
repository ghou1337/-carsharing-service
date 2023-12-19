package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.validator.UserAuthenticationValidator;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final UserService userService;
    private final UserAuthenticationValidator userAuthenticationValidator;

    @Autowired
    public AuthController(UserService userService, UserAuthenticationValidator userAuthenticationValidator) {
        this.userService = userService;
        this.userAuthenticationValidator = userAuthenticationValidator;
    }

    @GetMapping("/login")
    public String login() {
        return "authentication-page";
    }

    @GetMapping("/register")
    public String getPage(@ModelAttribute("user") User user) {
        return "registration-page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user")@Valid User user, BindingResult bindingResult, Model model) {
        userAuthenticationValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            return "registration-page";
        }
        userService.registerNewUser(user);
        return "redirect:/registration-page";
    }
}

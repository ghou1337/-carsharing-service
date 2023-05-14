package pl.messages.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.messages.web.dao.UserDAO;
import pl.messages.web.model.User;

//@Controller
//public class LoginController {
//
//    private final UserDAO userDAO;
//
//    @Autowired
//    public LoginController(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }

//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login-page";
//    }
//
//    @PostMapping("/login")
//    public String handleLoginRequest(@RequestParam String login,
//                                     @RequestParam String password,
//                                     Model model) {
//        User user = userDAO.findIntoTable(login, password);
//        if(user != null) {
//            model.addAttribute("user", user);
//            return "redirect:/main";
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "login-page";
//        }
//    }
//}

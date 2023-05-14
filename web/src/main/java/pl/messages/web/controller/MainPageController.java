package pl.messages.web.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.messages.web.dao.UserDAO;
import pl.messages.web.dao.VehicleDAO;
import pl.messages.web.model.Vehicle;

@Controller
public class MainPageController {
    private final VehicleDAO vehicleDAO;
    private final UserDAO userDAO;

    @Autowired
    public MainPageController(VehicleDAO vehicleDAO, UserDAO userDAO) {
        this.vehicleDAO = vehicleDAO;
        this.userDAO = userDAO;
    }
    @GetMapping("/main")
    public String index(UserDAO user, Model model) {
        model.addAttribute("vehicles", vehicleDAO.index());
        model.addAttribute("user", userDAO.currentUser());
        return "main";
    }

    @GetMapping("/new-vehicle")
    public String newCar(@ModelAttribute("vehicle") Vehicle vehicle) {
        return "new-vehicle";
    }

    @PostMapping("/new-vehicle")
    public String addCar(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleDAO.addNewCar(vehicle);
        return "redirect:/main";
    }
}

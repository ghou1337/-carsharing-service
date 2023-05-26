package pl.messages.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.messages.web.dao.UserDAO;
import pl.messages.web.dao.VehicleDAO;
import pl.messages.web.model.Vehicle;

@Controller
public class MainPageController {
    private final VehicleDAO vehicleDAO;
    private UserDAO userDAO;

    @Autowired
    public MainPageController(VehicleDAO vehicleDAO, UserDAO userDao) {
        this.vehicleDAO = vehicleDAO;
        this.userDAO = userDao;
    }
    @GetMapping("/main")
    public String index(Model model) {
        model.addAttribute("vehicles", vehicleDAO.index());
        model.addAttribute("rentedUserVehicle", vehicleDAO.rentVehicle());
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

    @PatchMapping("/main/{id}")
    public String rentVehicle(@PathVariable("id") int id) {
        vehicleDAO.userVehicle(id);
        return "redirect:/main";
    }
}

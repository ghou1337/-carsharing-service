package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import java.util.List;

@Controller
public class MainPageController {
    private final UserService userService;
    private final VehicleService vehicleService;

    @Autowired
    public MainPageController(UserService userService, VehicleService vehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/main")
    public String showMainPage(@RequestParam(required = false) String carBrand, Model model) {
        List<Vehicle> vehicles;
        if (carBrand != null) {
            vehicles = vehicleService.searchCar(carBrand);
        } else {
            vehicles = vehicleService.getAllCars();
        }
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("user_car", vehicleService.getUserCar());
        model.addAttribute("user_data", userService.getAuthenticatedUser());
        return "main";
    }

    @PatchMapping("/main/{id}")
    public String updateCar(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            vehicleService.saveUserCar(userService.getAuthenticatedUser(), id);
            redirectAttributes.addFlashAttribute("success_message", "The car was successfully rented!");
        } catch (NotEnoughBalanceException e) {
            redirectAttributes.addFlashAttribute("error_message", e.getMessage() );
        }
        return "redirect:/main";
    }

    @DeleteMapping("/main/delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        vehicleService.deleteUserCar(id);
        return "redirect:/main";
    }
}

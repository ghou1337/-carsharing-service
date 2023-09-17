package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.RentedVehicleService;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import java.util.List;

@Controller
public class MainPageController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentedVehicleService rentedVehicleService;

    @Autowired
    public MainPageController(UserService userService, VehicleService vehicleService, RentedVehicleService rentedVehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.rentedVehicleService = rentedVehicleService;
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllCars());
        model.addAttribute("user_car", rentedVehicleService.getUserCars(userService.getAuthenticatedUser()));
        model.addAttribute("user_data", userService.getAuthenticatedUser());
        return "main";
    }

    @PatchMapping("/main/{vehicleId}")
    public String rentCar(@PathVariable("vehicleId") int vehicleId, RedirectAttributes redirectAttributes) {
        try {
            rentedVehicleService.rentCar(userService.getAuthenticatedUser(), vehicleId);

            redirectAttributes.addFlashAttribute("success_message", "The car was successfully rented!");
            redirectAttributes.addFlashAttribute("timer", System.currentTimeMillis());
        } catch (NotEnoughBalanceException e) {
            redirectAttributes.addFlashAttribute("error_message", e.getMessage() );
        }
        return "redirect:/main";
    }

    @DeleteMapping("/main/delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        rentedVehicleService.completeLease(id);
        return "redirect:/main";
    }

    public Long timerForRent() {
        return System.currentTimeMillis();
    }
}

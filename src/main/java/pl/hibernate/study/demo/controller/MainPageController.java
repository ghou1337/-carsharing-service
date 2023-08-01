package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;

import java.util.List;

@Controller
public class MainPageController {
    private final UserService userService;

    private final VehicleService vehicleService;
    private final VehicleRepo vehicleRepo;

    @Autowired
    public MainPageController(UserService userService, VehicleService vehicleService,
                              VehicleRepo vehicleRepo) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.vehicleRepo = vehicleRepo;
    }

//    @GetMapping("/showUserInfo")
//    public String showUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        UserDetailsConfig userDetailsConfig = (UserDetailsConfig)authentication.getPrincipal();
//        return "redirect:/main";
//    }

    @PatchMapping("/main/{id}")
    public String updateCar(@PathVariable("id") int id) {
        vehicleService.saveUserCar(userService.findUserById(1), id);
        return "redirect:/main";
    }

    @DeleteMapping("/main/delete/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        vehicleService.deleteUserCar(id);
        return "redirect:/main";
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
        model.addAttribute("user_car", vehicleService.getUserCar(userService.findUserById(1)));
        return "main";
    }
}

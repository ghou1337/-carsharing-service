package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.RentedVehicleHistoryService;
import pl.hibernate.study.demo.service.RentingVehicleService;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

@Controller
public class MainPageController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;

    @Autowired
    public MainPageController(UserService userService, VehicleService vehicleService,
                              RentingVehicleService rentingVehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.rentingVehicleService = rentingVehicleService;
    }

    private User getUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping("/main")
    public String showMainPage(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllCars());
        model.addAttribute("user_car", rentingVehicleService.getAllUserCars(getUser()));
        model.addAttribute("user_data", getUser());
        return "main";
    }

    @PatchMapping("/main/{vehicleId}")
    public String rentCar(@PathVariable("vehicleId") int vehicleId, RedirectAttributes redirectAttributes) {
        try {
            rentingVehicleService.rentCar(getUser(), vehicleId);
            redirectAttributes.
                    addFlashAttribute("success_message",
                            "The car was successfully rented!");
        } catch (NotEnoughBalanceException e) {
            redirectAttributes.
                    addFlashAttribute("error_message", e.getMessage() );
        }

        return "redirect:/main";
    }

    @DeleteMapping("/main/delete/{id}")
    public String deleteCar(@PathVariable("id") int carId) {
        rentingVehicleService.completeLease(carId, getUser());
        return "redirect:/main";
    }


}

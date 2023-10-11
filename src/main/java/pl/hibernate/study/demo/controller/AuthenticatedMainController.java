package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.service.RentingVehicleService;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import java.util.List;

@Controller
@RequestMapping("/main")
public class AuthenticatedMainController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;
    private Boolean searchFilter = false;

    @Autowired
    public AuthenticatedMainController(UserService userService, VehicleService vehicleService,
                                       RentingVehicleService rentingVehicleService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.rentingVehicleService = rentingVehicleService;
    }

    private User getUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping
    public String showMainPage(@ModelAttribute("search_filter_updated") VehicleSearchFilter vehicleSearchFilter,
                               Model model) {
        List<Vehicle> vehicles;
        if(searchFilter == false) {
            vehicles = vehicleService.getAllCars();
        } else {
            vehicles = vehicleService.getAllCarsWithFilter(vehicleSearchFilter);
        }
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("user_car", rentingVehicleService.getAllUserCars(getUser()));
        model.addAttribute("user_data", getUser());
        return "main-authenticated-page";
    }
    @PostMapping("/reset-filter")
    public String resetFilter() {
        searchFilter = false;
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String searchFilter(VehicleSearchFilter vehicleSearchFilter,
                               RedirectAttributes redirectAttributes) {
        searchFilter = true;
        redirectAttributes.addFlashAttribute("search_filter_updated", vehicleSearchFilter);
        return "redirect:/main";
    }

    @PatchMapping("/{vehicleId}")
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

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") int carId) {
        rentingVehicleService.completeLease(carId, getUser());
        return "redirect:/main";
    }


}

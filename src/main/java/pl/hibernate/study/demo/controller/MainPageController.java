package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.modelDTO.VehicleSearchFilterDTO;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.service.RentingVehicleService;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainPageController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;

    @Autowired
    private VehicleSearchFilterDTO vehicleSearchFilterDTO;

    private Boolean searchFilter = false;
    private final VehicleRepo vehicleRepo;

    @Autowired
    public MainPageController(UserService userService, VehicleService vehicleService,
                              RentingVehicleService rentingVehicleService,
                              VehicleRepo vehicleRepo) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.rentingVehicleService = rentingVehicleService;
        this.vehicleRepo = vehicleRepo;
    }

    private User getUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping
    public String showMainPage(@ModelAttribute("search_filter_updated") VehicleSearchFilterDTO vehicleSearchFilterDTO,
                               Model model) {
        List<Vehicle> vehicles;
        if(searchFilter == false) {
            vehicles = vehicleService.getAllCars();
        } else {
            vehicles = vehicleService.getAllCarsWithFilter(vehicleSearchFilterDTO);
        }
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("user_car", rentingVehicleService.getAllUserCars(getUser()));
        model.addAttribute("user_data", getUser());
        return "main";
    }
    @PostMapping("/reset-filter")
    public String resetFilter() {
        searchFilter = false;
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String searchFilter(VehicleSearchFilterDTO vehicleSearchFilterDTO,
                               RedirectAttributes redirectAttributes) {
        searchFilter = true;
        redirectAttributes.addFlashAttribute("search_filter_updated", vehicleSearchFilterDTO);
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

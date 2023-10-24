package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.CarClassFilter;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.service.RentedVehicleHistoryService;
import pl.hibernate.study.demo.service.RentingVehicleService;
import pl.hibernate.study.demo.service.UserService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;
import pl.hibernate.study.demo.service.exe.RentHistoryWasNotRecorded;

import java.util.List;

@Controller
@RequestMapping("/main")
public class AuthenticatedMainController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;
    private final RentedVehicleHistoryService rentedVehicleHistoryService;
    private Boolean searchFilter = false;

    private Boolean classFilter = false;

    @Autowired
    public AuthenticatedMainController(UserService userService, VehicleService vehicleService,
                                       RentingVehicleService rentingVehicleService, RentedVehicleHistoryService rentedVehicleHistoryService) {
        this.userService = userService;
        this.vehicleService = vehicleService;
        this.rentingVehicleService = rentingVehicleService;
        this.rentedVehicleHistoryService = rentedVehicleHistoryService;
    }

    private User getUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping
    public String showMainPage(@ModelAttribute("search_filter_updated") VehicleSearchFilter vehicleSearchFilter, Vehicle vehicle, Model model) {
        model.addAttribute("search_filter_updated", vehicleSearchFilter);
        List<Vehicle> vehicles = (!searchFilter)
                ? vehicleService.getAllCars() : vehicleService.getAllCarsWithFilter(vehicleSearchFilter);
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
        System.out.println(vehicleSearchFilter.toString() + " " + searchFilter);
        return "redirect:/main";
    }

    @PatchMapping("/{vehicleId}")
    public String startRent(@PathVariable("vehicleId") int vehicleId, RedirectAttributes redirectAttributes) {
        Vehicle rentingCar = vehicleService.getCarById(vehicleId);
        try {
            rentingVehicleService.rentCar(getUser(), vehicleId);
            redirectAttributes.
                    addFlashAttribute("success_message", "The car was successfully rented!");
        } catch (NotEnoughBalanceException e) {
            redirectAttributes.addFlashAttribute("error_message", e.getMessage() );
        }
        // car hash for same unique hash in actual renting car and history record
        String carHash = rentingVehicleService.getRentingCarByCarIdAndUserId(vehicleId, getUser()).getHash();
        try {
            // starting rent history record
            rentedVehicleHistoryService.startRentHistory(getUser(), rentingCar, carHash);
        } catch (RentHistoryWasNotRecorded e) {
            throw new RuntimeException("Error: Car was rented but history table didn't record this action", e);
        }
        return "redirect:/main";
    }

    @DeleteMapping("/delete/{id}")
    public String completeLease(@PathVariable("id") int carId) {
        String carHash = rentingVehicleService.getRentingCarByCarIdAndUserId(carId, getUser()).getHash();

        rentedVehicleHistoryService.completeLeaseHistoryRecord(carId, getUser(), carHash);
        rentingVehicleService.completeLeaseActualRentingTable(carId, getUser());
        vehicleService.setCarAvailable(carId);
        return "redirect:/main";
    }

//    @PatchMapping("/car-class")
//    public String classFilter(@ModelAttribute("carClass") CarClassFilter carClassFilter) {
//        System.out.println("path: " + carClassFilter.getCarClass());
//        return "redirect:/main";
//    }
}

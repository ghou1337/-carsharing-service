package pl.hibernate.study.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.service.*;
import pl.hibernate.study.demo.service.exe.HashNotFoundException;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;
import pl.hibernate.study.demo.service.exe.RentHistoryWasNotRecorded;
import pl.hibernate.study.demo.service.search_filter.VehicleSearchFilterService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class AuthenticatedMainController {
    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;
    private final RentedVehicleHistoryService rentedVehicleHistoryService;
    private final VehicleSearchFilterService vehicleSearchFilterService;
    private Boolean searchFilter = false;

    private Boolean classFilter = false;

    private User getUser() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping
    public String showMainPage(@ModelAttribute("search_filter_updated") @Valid VehicleSearchFilter vehicleSearchFilter, Model model) {
        model.addAttribute("search_filter_updated", vehicleSearchFilter);
        List<Vehicle> vehicles = (!searchFilter) ? vehicleService.getAllAvailableCars() : vehicleSearchFilterService.getAllCarsWithFilterBoundaries(vehicleSearchFilter);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("user_cars", rentingVehicleService.getAllUserCars(getUser()));
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

    @PostMapping("/{vehicleId}")
    public String startRent(@PathVariable("vehicleId") int carId, RedirectAttributes redirectAttributes) throws NotEnoughBalanceException{
        Vehicle rentingCar = vehicleService.getCarById(carId);
        try {
            rentingVehicleService.rentCar(getUser(), carId);
            vehicleService.setCarNotAvailable(carId);
            redirectAttributes.addFlashAttribute("success_message", "The car was successfully rented!");
        } catch (NotEnoughBalanceException e) {
            Map<Integer, String> errorMessages = new HashMap<>();
            errorMessages.put(carId, e.getMessage());
            redirectAttributes.addFlashAttribute("errorBalanceMessage", errorMessages);
            return "redirect:/main";
        }
        rentedVehicleHistoryService.startRentHistory(getUser(), rentingCar, rentingVehicleService.getRentingCarByCarIdAndUser(carId,getUser()).getHash());
        searchFilter = false;
        return "redirect:/main";
    }

    @DeleteMapping("/delete/{hash}")
    public String completeLease(@PathVariable("hash") String hash) {
        //leaseManagement.completeLease(hash);
        int carId = rentingVehicleService.completeLease(hash, getUser());
        rentedVehicleHistoryService.completeLeaseHistoryRecord(hash); // в контрол
        vehicleService.setCarAvailable(carId);

        searchFilter = false;
        return "redirect:/main";
    }
}

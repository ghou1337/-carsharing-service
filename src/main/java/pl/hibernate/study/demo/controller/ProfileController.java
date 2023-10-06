package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.RentedVehicleHistoryService;
import pl.hibernate.study.demo.service.UserService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    //@ModelAttribute("rentedCars")List<RentedVehicle> rentedVehicle

    @Autowired
    private UserService userService;

    @Autowired
    private RentedVehicleHistoryService rentedVehicleHistoryService;

    @GetMapping()
    public String userProfileData(Model model) {
        User user = userService.getAuthenticatedUser();
        model.addAttribute("user", user);
        model.addAttribute("rentedCars", rentedVehicleHistoryService.getAllRentedCars(user));
        return "profile-page";
    }

//    public float getMoneySpent(User user) {
//        List<Vehicle> vehicles = rentedVehicleService.getAllRentedCars(user)
//                .stream()
//                .map(RentedVehicle::getRentedCar)
//                .collect(Collectors.toList());
//        List<Integer> prices = vehicles.stream().map(Vehicle::getPriceRent).collect(Collectors.toList());
//        List<Integer> moneySpent;
////        for (moneySpent: prices
////             ) {
////
////        }
//    }
}

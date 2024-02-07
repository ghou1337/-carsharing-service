package pl.hibernate.study.demo.admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.RentingVehicleService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.validator.VehicleAdminRecordValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminMainPage {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleAdminRecordValidator vehicleAdminRecordValidator;

    @Autowired
    private RentingVehicleService rentingVehicleService;

    @GetMapping("/add-new-car")
    public String addPage(@ModelAttribute("vehicle") @Valid Vehicle vehicle) {
        return "/admin_page/add-new-car-page";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("renting_vehicles", rentingVehicleService.getAllRentingCars());
        return "/admin_page/admin-main-page";
    }

//    @DeleteMapping("/end-rent/{hash}")
//    public String endRent() {String hash} {
//
//        return "redirect:/admin_page/admin-main-page";
//    }
    @PostMapping("/add-new-car")
    public String addNewCar(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult bindingResult) {
        vehicleAdminRecordValidator.validate(vehicle, bindingResult);
        if(bindingResult.hasErrors()){
            return "/admin_page/add-new-car-page";
        }
        vehicleService.saveNewCar(vehicle);
        return "redirect:/admin/add-new-car";
    }
}

package pl.hibernate.study.demo.admin_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.LeaseManagement;
import pl.hibernate.study.demo.service.RentingVehicleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMainController {

    private final RentingVehicleService rentingVehicleService;

    private final LeaseManagement leaseManagement;

    @GetMapping("/main")
    public String mainPage(Model model, Vehicle vehicle) {
        model.addAttribute("renting_vehicles", rentingVehicleService.getAllRentingCars());
        model.addAttribute("vehicle", vehicle);
        return "admin/admin-main-page";
    }

    @DeleteMapping("/end-rent/{hash}")
    public String completeLeaseByAdmin(@PathVariable("hash") String hash) {
        leaseManagement.completeLeaseByAdmin(hash);
        return "redirect:/admin/main";
    }

}

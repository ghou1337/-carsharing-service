package pl.hibernate.study.demo.admin_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.VehicleService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAllCarsController {

    private final VehicleService vehicleService;

    @GetMapping("/all")
    public String allCarsPage(Vehicle vehicle, Model model) {
        model.addAttribute("vehicles", vehicleService.getAllCars());
        return "admin/all-cars-page";
    }
}

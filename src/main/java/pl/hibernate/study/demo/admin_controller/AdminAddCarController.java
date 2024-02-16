package pl.hibernate.study.demo.admin_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.validator.VehicleAdminRecordValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAddCarController {

    private final VehicleService vehicleService;
    private final VehicleAdminRecordValidator vehicleAdminRecordValidator;

    @GetMapping("/add-car")
    public String mainPage(Model model, Vehicle vehicle) {
        model.addAttribute("vehicle", vehicle);
        return "admin/add-car-page";
    }

    @PostMapping("/add-car")
    public String addNewCar(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult bindingResult) {
        vehicleAdminRecordValidator.validate(vehicle, bindingResult);
        if(bindingResult.hasErrors()){
            return "/admin/add-car-page";
        }
        vehicleService.saveNewCar(vehicle);
        return "redirect:/admin/add-car";
    }
}

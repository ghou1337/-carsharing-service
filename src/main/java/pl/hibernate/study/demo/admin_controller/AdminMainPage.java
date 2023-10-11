package pl.hibernate.study.demo.admin_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/admin")
public class AdminMainPage {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleAdminRecordValidator vehicleAdminRecordValidator;

    @GetMapping("/add-new-car")
    public String addPage(@ModelAttribute("vehicle") @Valid Vehicle vehicle) {
        return "/admin_page/add-new-car-page";
    }

    @PostMapping("/add-new-car")
    public String addNewCar(@ModelAttribute("vehicle") @Valid Vehicle vehicle, BindingResult bindingResult) {
        vehicleAdminRecordValidator.validate(vehicle, bindingResult);
        if(bindingResult.hasErrors()){
            return "/admin_page/add-new-car-page";
        }
        vehicleService.saveCar(vehicle);
        return "redirect:/admin/add-new-car";
    }
}

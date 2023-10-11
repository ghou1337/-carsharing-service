package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.service.VehicleService;

import java.util.List;

@Controller
public class PublicMainController {
    @Autowired
    private VehicleService vehicleService;
    private Boolean searchFilter = false;

    @GetMapping("/public")
    public String showMainPage(@ModelAttribute("search_filter_updated") VehicleSearchFilter vehicleSearchFilter,
                               Model model) {
        List<Vehicle> vehicles;
        if(searchFilter == false) {
            vehicles = vehicleService.getAllCars();
        } else {
            vehicles = vehicleService.getAllCarsWithFilter(vehicleSearchFilter);
        }
        model.addAttribute("vehicles", vehicles);
        return "main-page";
    }
}

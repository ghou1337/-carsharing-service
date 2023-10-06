package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.VehicleService;

//@Controller
//@RequestMapping("/search-by-name")
//public class MainPageFilterController {
//    @Autowired
//    private  VehicleService vehicleService;
//
//    @GetMapping
//
//    private String page(@ModelAttribute("vehicleAttributes") Vehicle vehicleAttributes) {
//        return "main";
//    }
//    @PostMapping()
//    public String searchFilter(@ModelAttribute("vehicleAttributes") Vehicle vehicleAttributes, Model model) {
//        vehicleService.getAllCarsWithFilter(vehicleAttributes);
//        System.out.println("AAAAAAAAAAAAAAAAAAA: " + vehicleAttributes.toString());
//        return "redirect:/main";
//    }
//}

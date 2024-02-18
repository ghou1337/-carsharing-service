package pl.hibernate.study.demo.admin_controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleImage;
import pl.hibernate.study.demo.service.RentedVehicleHistoryService;
import pl.hibernate.study.demo.service.VehicleImageService;
import pl.hibernate.study.demo.service.VehicleService;

import java.io.IOException;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminAllCarsController {

    private final VehicleService vehicleService;

    private final VehicleImageService vehicleImageService;

    private final RentedVehicleHistoryService rentedVehicleHistoryService;

    @GetMapping("/all")
    public String allCarsPage(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllCars());
        return "admin/all-cars-page";
    }

    @PatchMapping("update-image/{id}")
    public String newImage(@PathVariable("id") int vehicleId, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        Vehicle vehicle = vehicleService.getCarById(vehicleId);
        try {
            String imageName = vehicleImageService.uploadImage(file, vehicle);
            redirectAttributes.addFlashAttribute("message", "Файл успешно загружен и сохранен как " + imageName);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }
        return "redirect:/admin/all";
    }

    @PostMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable("id") int vehicleId, RedirectAttributes redirectAttributes) {
        try {
            vehicleService.deleteById(vehicleId);
            redirectAttributes.addFlashAttribute("message", "Car was deleted successfully");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("message", "Delete error");
        }

        return "redirect:/admin/all";
    }
}

package pl.hibernate.study.demo.admin_controller;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleImage;
import pl.hibernate.study.demo.service.VehicleImageService;
import pl.hibernate.study.demo.service.VehicleService;
import pl.hibernate.study.demo.validator.VehicleAdminRecordValidator;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAddCarController {

    private final VehicleService vehicleService;
    private final VehicleAdminRecordValidator vehicleAdminRecordValidator;

    private final VehicleImageService vehicleImageService;
    @GetMapping("/add-car")
    public String mainPage(Model model, Vehicle vehicle) {
        model.addAttribute("vehicle", vehicle);
        return "admin/add-car-page";
    }

    @PostMapping("/add-car")
    public String addNewCar(@ModelAttribute("vehicle") @Valid Vehicle vehicle, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, BindingResult bindingResult) {
        vehicleAdminRecordValidator.validate(vehicle, bindingResult);
        if(bindingResult.hasErrors()){
            return "/admin/add-car-page";
        }
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Пожалуйста, выберите файл для загрузки.");
            return "redirect:/admin/add-car";
        }
        try {
            String imageName = vehicleImageService.uploadImage(file, vehicle);
            redirectAttributes.addFlashAttribute("message", "Файл успешно загружен и сохранен как " + imageName);
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "Ошибка при загрузке файла: " + e.getMessage());
        }
        vehicleService.saveNewCar(vehicle);
        redirectAttributes.addFlashAttribute("message", "Upload has done successfully");
        return "redirect:/admin/add-car";
    }
}

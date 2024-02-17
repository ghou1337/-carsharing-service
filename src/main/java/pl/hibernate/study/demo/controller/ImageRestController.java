package pl.hibernate.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.hibernate.study.demo.model.VehicleImage;
import pl.hibernate.study.demo.repos.VehicleImageRepo;

@RestController
public class ImageRestController {
    @Autowired
    private VehicleImageRepo vehicleImageRepo; // Предполагается, что у вас есть репозиторий для изображений

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        VehicleImage vehicleImage = vehicleImageRepo.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));

        byte[] imageContent = vehicleImage.getContent(); // Получаем содержимое изображения из вашей сущности

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.parseMediaType(vehicleImage.getContentType())) // Устанавливаем тип содержимого
                .body(imageContent);
    }
}

package pl.hibernate.study.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleImage;
import pl.hibernate.study.demo.repos.VehicleImageRepo;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class VehicleImageService {

    private final VehicleImageRepo vehicleImageRepo;

    @Transactional
    public void newImage(VehicleImage vehicleImage) {
        vehicleImageRepo.save(vehicleImage);
    }

    @Transactional
    public String uploadImage(MultipartFile file, Vehicle vehicle) throws IOException {
        VehicleImage vehicleImage = new VehicleImage();
        vehicleImage.setName(vehicle.toString());
        vehicleImage.setContent(file.getBytes());
        vehicleImage.setContentType(file.getContentType());
        vehicleImage.setSize(file.getSize());

        newImage(vehicleImage);
        vehicle.setVehicleImage(vehicleImage);
        return vehicleImage.getName();
    }
}

package pl.hibernate.study.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.VehicleImage;
import pl.hibernate.study.demo.repos.VehicleImageRepo;

@Service
@RequiredArgsConstructor
public class VehicleImageService {

    private final VehicleImageRepo vehicleImageRepo;

    @Transactional
    public void newImage(VehicleImage vehicleImage) {
        vehicleImageRepo.save(vehicleImage);
    }
}

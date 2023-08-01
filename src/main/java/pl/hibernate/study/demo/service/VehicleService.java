package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.model.User;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    public List<Vehicle> searchCar(String brand) {
        return vehicleRepo.getVehicleByCARBRAND(brand);
    }
    public List<Vehicle> getUserCar(User user) {
        return vehicleRepo.getVehicleByUserVehicle(user);
    }

    public List<Vehicle> getAllCars() {
        return vehicleRepo.findAllByUserVehicleIsNull();
    }
    public void saveUserCar(User user, int id) {
        vehicleRepo.setUserCar(user, id);
    }

    public void deleteUserCar(int id) {
        vehicleRepo.setNullUserCar(id);
    }
}

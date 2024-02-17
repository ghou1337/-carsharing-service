package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    public List<Vehicle> getAllCars() {
        return vehicleRepo.findAll();
    }
    public List<Vehicle> getAllAvailableCars() {
        return vehicleRepo.getAllByAvailabilityIsTrue();
    }

    public Vehicle getCarById(int carID) {
        if(vehicleRepo.findById(carID).isPresent()) {
            return vehicleRepo.findById(carID).get();
        }else throw new RuntimeException("Car was not found");
    }
    @Transactional
    public void setCarNotAvailable(int id) {
        vehicleRepo.setAvailabilityToFalse(id);
    }
    @Transactional
    public void setCarAvailable(int id) {
        vehicleRepo.setAvailabilityToTrue(id); // do car available
    }
    @Transactional
    public void saveNewCar(Vehicle vehicle) {
        vehicle.setAvailability(true);
        vehicleRepo.save(vehicle); // save new car and make available
    }
}

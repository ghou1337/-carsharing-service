package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.model.VehicleSearchFilter;
import pl.hibernate.study.demo.repos.VehicleRepo;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private  VehicleRepo vehicleRepo;

    public List<Vehicle> getAllCarsWithFilter(VehicleSearchFilter filter) {
//        if(filter.isEmpty()) {
//            filter.setBasicValues();
//        }
        return vehicleRepo.getAllByCarBrandOrPriceRentOrCarYear(
                (filter.getCarBrand().isEmpty() ? null : filter.getCarBrand()),
                (filter.getPriceRent().isEmpty() ? null : Integer.parseInt(filter.getPriceRent())),
                (filter.getCarYear().isEmpty() ? null : Integer.parseInt(filter.getCarYear())),
                (filter.getCarBodyType().equals("ALL") ? null : filter.getCarBodyType()));
    }

    public List<Vehicle> getAllCars() {
        return vehicleRepo.getAllByAvailabilityIsTrue();
    }

    public Vehicle getCarById(int carID) {
        if(vehicleRepo.findById(carID).isPresent()) {
            return vehicleRepo.findById(carID).get();
        }else throw new RuntimeException("Car wan not found");
    }
    @Transactional
    public void setCarNotAvailable(int id) {
        vehicleRepo.setAvailabilityToFalse(id);
    }
    @Transactional
    public void setCarAvailable(int id) {
        // making car available
        vehicleRepo.setAvailabilityToTrue(id);
    }
    @Transactional
    public void saveNewCar(Vehicle vehicle) {
        // save new car
        vehicle.setAvailability(true);
        vehicleRepo.save(vehicle);
    }
}

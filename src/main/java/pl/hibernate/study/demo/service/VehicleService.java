package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.modelDTO.VehicleSearchFilterDTO;
import pl.hibernate.study.demo.repos.VehicleRepo;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    @Autowired
    private  VehicleRepo vehicleRepo;

    public Vehicle getCarById(int carID) {
        if(vehicleRepo.findById(carID).isPresent()) {
            return vehicleRepo.findById(carID).get();
        }else
            throw new RuntimeException("Car wan not found");
    }

    public void setNullForRentedCar(int id) {
        vehicleRepo.setAvailabilityToFalse(id);
    }

    public void carAvailable(int id) {
        vehicleRepo.setAvailabilityToTrue(id);
    }

    public List<Vehicle> getAllCars() {
        return vehicleRepo.getAllByAvailabilityIsTrue();
    }

    public List<Vehicle> getAllCarsWithFilter(VehicleSearchFilterDTO filter) {
        filter.setCarYear(filter.getCarYear().isEmpty() ? "0" : filter.getCarYear());
        filter.setPriceRent(filter.getPriceRent().isEmpty() ? "0" : filter.getPriceRent());

        return vehicleRepo.getAllByCarBrandOrPriceRentOrCarYear(
                filter.getCarBrand(),
                Integer.parseInt(filter.getPriceRent()),
                Integer.parseInt(filter.getCarYear()));
    }

    public void saveCar(Vehicle vehicle) {
        vehicle.setAvailability(true);
        vehicleRepo.save(vehicle);
    }
}

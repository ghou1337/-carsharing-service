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
        return vehicleRepo.getAllByCarBrandOrPriceRentOrCarYear(
                (filter.getCarBrand().isEmpty() ? null : filter.getCarBrand()),
                (filter.getPriceRent().isEmpty() ? null : Integer.parseInt(filter.getPriceRent())),
                (filter.getCarYear().isEmpty() ? null : Integer.parseInt(filter.getCarYear())),
                (filter.getCarBodyType().equals("ALL") ? null : filter.getCarBodyType()));
    }

    public List<Vehicle> getAllCarsWithFilterWithinTheBoundaries(VehicleSearchFilter filter) {
        return vehicleRepo.getAllCarsByFilterValues(
                (filter.getCarBrand().isEmpty() ? null : filter.getCarBrand()),
                (filter.getMinPriceRent().isEmpty() ? null : Integer.parseInt(filter.getMinPriceRent())),
                (filter.getMaxPriceRent().isEmpty() ? null : Integer.parseInt(filter.getMaxPriceRent())),
                (filter.getMinCarYear().isEmpty() ? null : Integer.parseInt(filter.getMinCarYear())),
                (filter.getMaxCarYear().isEmpty() ? null : Integer.parseInt(filter.getMaxCarYear())),
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
        vehicleRepo.setAvailabilityToTrue(id); // do car available
    }
    @Transactional
    public void saveNewCar(Vehicle vehicle) {
        vehicle.setAvailability(true);
        vehicleRepo.save(vehicle); // save new car and make available
    }
}

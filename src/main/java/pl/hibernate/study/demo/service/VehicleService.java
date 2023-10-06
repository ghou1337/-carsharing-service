package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
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

    public List<Vehicle> getAllCarsWithFilter(Vehicle filter) {
        return vehicleRepo.getAllByAvailabilityIsTrueAndCarYearIsAndCarBrandIsAndPriceRentIs(
                filter.getCarYear(),
                filter.getCarBrand(),
                filter.getPriceRent());
    }

    public void saveCar(Vehicle vehicle) {
        vehicle.setAvailability(true);
        vehicleRepo.save(vehicle);
    }


//
//    public List<Vehicle> searchCar(String brand) {
//        return vehicleRepo.getVehicleByCarBrand(brand);
//    }

//    //rv serivce needed
//    public List<Vehicle> getUserCar() {
//        return rentedVehicleRepo.getRentedVehiclesByCarRenter(userService.getAuthenticatedUser());
//    }
//    //rv serivce needed
//    public void carWasRented() {
//        return vehicleRepo.;
//    }
//    public void saveUserCar(User user, int carId) throws NotEnoughBalanceException {
//        User userRenter = userService.getAuthenticatedUser();
//        float actualMoney = userRenter.getMoney();
//        float priceRent = getCarById(carId).getPriceRent();
//        if (actualMoney >= priceRent) {
//            userService.getAuthenticatedUser().setMoney(actualMoney - priceRent);
//            vehicleRepo.setUserCar(user, carId);
//        } else {
//            throw new NotEnoughBalanceException("Not enough money for the rent!");
//        }
//    }

//    public void deleteUserCar(int id) {
//        vehicleRepo.setNullUserCar(id);
//    }
}

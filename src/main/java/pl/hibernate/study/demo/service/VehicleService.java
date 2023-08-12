package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import java.util.List;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepo vehicleRepo;

    private final UserService userService;

    public VehicleService(VehicleRepo vehicleRepo, UserService userService) {
        this.vehicleRepo = vehicleRepo;
        this.userService = userService;
    }

    public List<Vehicle> searchCar(String brand) {
        return vehicleRepo.getVehicleByCARBRAND(brand);
    }
    public List<Vehicle> getUserCar() {
        return vehicleRepo.getVehicleByUserVehicle(userService.getAuthenticatedUser());
    }

    public List<Vehicle> getAllCars() {
        return vehicleRepo.findAllByUserVehicleIsNull();
    }

    public Vehicle getCarById(int carID) {
        if(vehicleRepo.findById(carID).isPresent()) {
            return vehicleRepo.findById(carID).get();
        }else
            throw new RuntimeException("Car wan not found");
    }
    public void saveUserCar(User user, int carId) throws NotEnoughBalanceException {
        User userRenter = userService.getAuthenticatedUser();
        float actualMoney = userRenter.getMoney();
        float priceRent = getCarById(carId).getPRICE_RENT();
        if (actualMoney >= priceRent) {
            userService.getAuthenticatedUser().setMoney(actualMoney - priceRent);
            vehicleRepo.setUserCar(user, carId);
        } else {
            throw new NotEnoughBalanceException("Not enough money for the rent!");
        }
    }

    public void deleteUserCar(int id) {
        vehicleRepo.setNullUserCar(id);
    }
}

package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentedVehicle;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.RentedVehicleRepo;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RentedVehicleService {
    @Autowired
    private RentedVehicleRepo rentedVehicleRepo;
    @Autowired
    private VehicleService vehicleService;

    public void rentCar(User user, int vehicleId) throws NotEnoughBalanceException{
        // whether the user has enough money
        Vehicle rentedVehicle = vehicleService.getCarById(vehicleId);
        float actualMoney = user.getMoney();
        float priceRent = rentedVehicle.getPriceRent();

        RentedVehicle rentedVehicles = new RentedVehicle();   //creating new car

        if (actualMoney >= priceRent) {
            user.setMoney(actualMoney - priceRent);
        } else {
            throw new NotEnoughBalanceException("Not enough money for the rent!");
        }
        // new rented car fields
        rentedVehicles.setCarRenter(user);
        rentedVehicles.setRentedCar(rentedVehicle);
        rentedVehicles.setRentStartAt(LocalDateTime.now());

        vehicleService.carWasRented(vehicleId); // car is not available during the rent so set false
        rentedVehicleRepo.save(rentedVehicles); // saving
    }

    public void completeLease(int id) {
        vehicleService.carAvailable(id);
        rentedVehicleRepo.deleteByRentedCar_Id(id);
    }
    public List<Vehicle> getUserCars(User user) {
        return rentedVehicleRepo.getRentedVehiclesByCarRenter(user).stream()
                .map(RentedVehicle::getRentedCar)
                .collect(Collectors.toList());
    }
}

package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentedVehicleHistory;
import pl.hibernate.study.demo.model.RentingVehicle;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.RentingVehicleRepo;
import pl.hibernate.study.demo.service.exe.NotEnoughBalanceException;
import pl.hibernate.study.demo.service.exe.RentHistoryWasNotRecorded;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RentingVehicleService  {
    @Autowired
    private RentingVehicleRepo rentingVehicleRepo;

    @Autowired
    private VehicleService vehicleService;

    public List<RentingVehicle> getAllRentingCars() {
        return rentingVehicleRepo.findAll();
    }
    public List<Vehicle> getAllUserCars(User user) {
        return rentingVehicleRepo.getAllByUser(user)
                .stream()
                .map(RentingVehicle::getVehicle)
                .collect(Collectors.toList());
    }

    public RentingVehicle getRentingCarByCarIdAndUser(int carId, User user){
        return rentingVehicleRepo.getByVehicle_IdAndUser_Id(carId, user.getId());
    }
    @Transactional
    public void completeLeaseActualRentingTable(int carId, User user) {
        rentingVehicleRepo.deleteByVehicle_IdAndUser(carId, user); // delete renting car from "renting_vehicles" table
    }
    @Transactional
    public void rentCar(User user, int vehicleId) throws NotEnoughBalanceException {
        Vehicle vehicleById = vehicleService.getCarById(vehicleId);
        Float actualMoney = user.getMoney();
        Float priceRent = vehicleById.getPriceRent();
        if (actualMoney >= priceRent) {
            user.setMoney(actualMoney - priceRent);
        } else {
            throw new NotEnoughBalanceException("Error: Not enough money for the rent!");
        }
        RentingVehicle rentingVehicle = new RentingVehicle();
        rentingVehicle.setVehicle(vehicleById);
        rentingVehicle.setUser(user);
        rentingVehicle.setHash(UUID.randomUUID().toString());
        rentingVehicleRepo.save(rentingVehicle);
    }
}

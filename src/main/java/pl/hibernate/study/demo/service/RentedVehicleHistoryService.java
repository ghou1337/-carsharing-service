package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentedVehicleHistory;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.RentedVehicleHistoryRepo;
import pl.hibernate.study.demo.service.exe.RentHistoryWasNotRecorded;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RentedVehicleHistoryService {
    @Autowired
    private RentedVehicleHistoryRepo rentedVehicleHistoryRepo;

    public void startRentHistory(User user, Vehicle vehicle, String hash) {
        RentedVehicleHistory rentedVehiclesHistory = new RentedVehicleHistory();
        rentedVehiclesHistory.setCarRenter(user);
        rentedVehiclesHistory.setRentedCar(vehicle);
        rentedVehiclesHistory.setRentStartAt(LocalDateTime.now());
        rentedVehiclesHistory.setHash(hash);
        try {
            rentedVehicleHistoryRepo.save(rentedVehiclesHistory); // starting rent history record
        } catch (RentHistoryWasNotRecorded e) {
            throw new RuntimeException("Error: Car was rented but history table didn't record this action", e);
        }
    }

    public void completeLeaseHistoryRecord(String hash) {
        // recording renting complete time by renting car hash
        rentedVehicleHistoryRepo.saveRentedTime(LocalDateTime.now(), hash);
    }

    public List<RentedVehicleHistory> getAllRentedCars(User user) {
        return rentedVehicleHistoryRepo.getAllByCarRenter_IdAndRentCompletionAtIsNotNull(user.getId());
    }
}

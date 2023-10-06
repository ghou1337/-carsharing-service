package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentedVehicleHistory;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.RentedVehicleHistoryRepo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class RentedVehicleHistoryService {
    @Autowired
    private RentedVehicleHistoryRepo rentedVehicleHistoryRepo;

    @Autowired
    private VehicleService vehicleService;

    public void startRentHistory(User user, Vehicle vehicle, String hash) {
        RentedVehicleHistory rentedVehiclesHistory = new RentedVehicleHistory();
        rentedVehiclesHistory.setCarRenter(user);
        rentedVehiclesHistory.setRentedCar(vehicle);
        rentedVehiclesHistory.setRentStartAt(LocalDateTime.now());
        rentedVehiclesHistory.setHash(hash);
        rentedVehicleHistoryRepo.save(rentedVehiclesHistory);
    }

    public void completeLeaseHistoryRecord(String hash) {
        rentedVehicleHistoryRepo.saveRentedTime(LocalDateTime.now(), hash);
//        rentedVehicleHistoryRepo.saveRentDuration(getRentalDuration(user, id), id);
    }

    public List<RentedVehicleHistory> getAllRentedCars(User user) {
        return rentedVehicleHistoryRepo.getAllByCarRenter_Id(user.getId());
    }

    public void rentingTime(RentedVehicleHistory rentedVehicleHistory) throws InterruptedException {
        LocalDateTime rentStartAt = rentedVehicleHistory.getRentStartAt();
        long hours, minutes, seconds;
        while (rentedVehicleHistory.getRentCompletionAt() == null ) {
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(rentStartAt, currentTime);

            hours = duration.toHours();
            minutes = duration.toMinutesPart();
            seconds = duration.toSecondsPart();

            String actualRentTime = hours + " часов, " + minutes + " минут, " + seconds + " секунд";
        }
    }

    // Reason of separating tables to history and actual renting. Logically that's better too.

//    public List<Vehicle> getUserCars(User user) {
//        return rentedVehicleHistoryRepo.getRentedVehiclesByCarRenterAndRentCompletionAtIsNull(user).stream()
//                .map(RentedVehicleHistory::getRentedCar)
//                .collect(Collectors.toList());
//    }

    //    private Long getRentalDuration(User user, int id) {
//        RentedVehicleHistory rentedVehicleHistory;
//        rentedVehicleHistory = rentedVehicleHistoryRepo.
//                getRentedVehicleHistoryByCarRenterAndRentedCar_IdAndRentCompletionAtIsNull(user, id);
//        return Duration.between(rentedVehicleHistory.getRentStartAt(),
//                                rentedVehicleHistory.getRentCompletionAt()).toMinutes();
//    }
}

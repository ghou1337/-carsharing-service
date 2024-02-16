package pl.hibernate.study.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hibernate.study.demo.model.RentingVehicle;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.exe.HashNotFoundException;

@Service
@RequiredArgsConstructor
public class LeaseManagement {

    private final UserService userService;
    private final VehicleService vehicleService;
    private final RentingVehicleService rentingVehicleService;
    private final RentedVehicleHistoryService rentedVehicleHistoryService;

    public void completeLease(String hash) {
        int carId = rentingVehicleService.getVehicleIdByHash(hash);
        try {
            rentedVehicleHistoryService.completeLeaseHistoryRecord(hash);
        } catch (HashNotFoundException e) {throw new HashNotFoundException("Hash wasn't found");}
        rentingVehicleService.completeLeaseActualRentingTable(hash, getUser());
        vehicleService.setCarAvailable(carId);
    }

    public void completeLeaseByAdmin(String hash) {
        int carId = rentingVehicleService.getVehicleIdByHash(hash);
        User user = rentingVehicleService.getUserIdByCarHash(hash);
        if(getUser().getRole() == "ADMIN") {
            rentedVehicleHistoryService.completeLeaseHistoryRecord(hash);
        }
        rentingVehicleService.completeLeaseActualRentingTable(hash, user);
        vehicleService.setCarAvailable(carId);
    }

    private User getUser() {
        return userService.getAuthenticatedUser();
    }
}

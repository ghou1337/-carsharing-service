package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hibernate.study.demo.model.RentedVehicle;
import pl.hibernate.study.demo.model.User;

import java.util.List;


public interface RentedVehicleRepo extends JpaRepository <RentedVehicle, Integer> {
    List<RentedVehicle> getRentedVehiclesByCarRenter(User user);

    void deleteByRentedCar_Id(int id);
}

package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentingVehicle;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.service.VehicleService;

import java.util.List;
@Repository
public interface RentingVehicleRepo extends JpaRepository <RentingVehicle, Integer> {
    List<RentingVehicle> getAllByUser(User user);

    RentingVehicle getByVehicle_IdAndUser_Id(int carId, int userId);
    @Transactional
    void deleteByVehicle_IdAndUser(int id, User user);
}

package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> getVehicleByUserVehicle(User user);

    List<Vehicle> getVehicleByCarBrand(String car_brand);

    List<Vehicle> findAllByUserVehicleIsNull();

    @Modifying
    @Query("update Vehicle v set v.userVehicle = ?1 where v.ID = ?2")
    void setUserCar(User user, int id);

    @Modifying
    @Query("update Vehicle v set v.userVehicle = null where v.ID = ?1")
    void setNullUserCar(int id);
}

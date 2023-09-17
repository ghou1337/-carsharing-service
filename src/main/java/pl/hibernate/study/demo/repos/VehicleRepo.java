package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.availability = false WHERE v.id = ?1")
    void setAvailabilityToFalse(int vehicleId);

    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.availability = true WHERE v.id = ?1")
    void setAvailabilityToTrue(int vehicleId);

    List<Vehicle> getAllByAvailabilityIsTrue();
}

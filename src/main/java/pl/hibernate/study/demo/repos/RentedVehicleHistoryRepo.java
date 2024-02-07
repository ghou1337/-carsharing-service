package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.RentedVehicleHistory;
import pl.hibernate.study.demo.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentedVehicleHistoryRepo extends JpaRepository <RentedVehicleHistory, Integer> {

    List<RentedVehicleHistory> getAllByCarRenter_IdAndRentCompletionAtIsNotNull(int userId);

    @Modifying
    @Transactional
    @Query("update RentedVehicleHistory rv set rv.rentCompletionAt =?1 where rv.hash =?2")
    void saveRentedTime(LocalDateTime localDateTime, String hash);

    @Modifying
    @Transactional
    @Query("update RentedVehicleHistory rv set rv.rentDuration =?1 where rv.rentedCar.id = ?2")
    void saveRentDuration(Long rentDuration, int id);
}

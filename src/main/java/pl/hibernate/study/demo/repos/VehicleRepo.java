package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;

import java.util.List;

public interface VehicleRepo extends JpaRepository<Vehicle, Integer>, JpaSpecificationExecutor<Vehicle> {
    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.availability = false WHERE v.id = ?1")
    void setAvailabilityToFalse(int vehicleId);

    @Transactional
    @Modifying
    @Query("UPDATE Vehicle v SET v.availability = true WHERE v.id = ?1")
    void setAvailabilityToTrue(int vehicleId);

    @Modifying
    @Query("SELECT v FROM Vehicle v " +
            "WHERE (:carBrand IS NULL OR v.carBrand = :carBrand) " +
            "AND (:priceRent IS NULL OR v.priceRent >= :priceRent) " +
            "AND (:carYear IS NULL OR v.carYear >= :carYear) " +
            "AND (:carBodyType IS NULL OR v.carClass = :carBodyType)" +
            "AND v.availability = true")
    List<Vehicle> getAllByCarBrandOrPriceRentOrCarYear(String carBrand, Integer priceRent, Integer carYear, String carBodyType);


    @Modifying
    @Query("SELECT v FROM Vehicle v " +
            "WHERE (:carBrand IS NULL OR v.carBrand = :carBrand) " +
            "AND (:minPriceRent IS NULL OR v.priceRent >= :minPriceRent) " +
            "AND (:maxPriceRent IS NULL OR v.priceRent <= :maxPriceRent) " +
            "AND (:minCarYear IS NULL OR v.carYear >= :minCarYear) " +
            "AND (:maxCarYear IS NULL OR v.carYear <= :maxCarYear) " +
            "AND (:carBodyType IS NULL OR v.carClass = :carBodyType)" +
            "AND v.availability = true")
    List<Vehicle> getAllCarsByFilterValues(String carBrand,
                                           Integer minPriceRent, Integer maxPriceRent,
                                           Integer minCarYear, Integer maxCarYear,
                                           String carBodyType);
    List<Vehicle> getAllByAvailabilityIsTrue();
}

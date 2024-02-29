package pl.hibernate.study.demo.repos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VehicleRepoJpaTest {
    @Autowired
    private VehicleRepo vehicleRepo;

    @Test
    void setCarNotAvailableDBTest() {
        Vehicle vehicle = new Vehicle(
                0,
                "Brand",
                150f,
                2023,
                true,
                "class1",
                "model1");
        vehicleRepo.save(vehicle);
        vehicleRepo.setAvailabilityToFalse(vehicle.getId());

        Vehicle updatedVehicle = vehicleRepo.findById(vehicle.getId()).orElse(null);

        assertTrue(updatedVehicle != null);
        assertFalse(updatedVehicle.getAvailability());
    }

    @Test
    void setCarAvailableDBTest() {
        Vehicle vehicle = new Vehicle(0, "Brand", 150f, 2023, false, "class1", "model1");
        vehicleRepo.save(vehicle);
        vehicleRepo.setAvailabilityToTrue(vehicle.getId());

        Vehicle updatedVehicle = vehicleRepo.findById(vehicle.getId()).orElse(null);

        assertTrue(updatedVehicle != null && updatedVehicle.getAvailability());
    }
}

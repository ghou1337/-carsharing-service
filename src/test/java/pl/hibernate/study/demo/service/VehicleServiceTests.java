package pl.hibernate.study.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.VehicleRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VehicleServiceTests {
    @InjectMocks
    private VehicleService vehicleService;

    @Mock
    private VehicleRepo vehicleRepo;

    private Vehicle mockVehicle0;

    private Vehicle mockVehicle1;
    private List<Vehicle> mockVehiclesList;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllCarsTest() {
        mockVehicle0 = new Vehicle(0, "Brand", 150, 2023, "url", true, "class1", "model1");
        mockVehicle1 = new Vehicle(1, "Brand2", 1000, 2048, "url", true, "class2", "model2");

        mockVehiclesList = new ArrayList<>();
        mockVehiclesList.add(mockVehicle0);
        mockVehiclesList.add(mockVehicle1);

        when(vehicleRepo.getAllByAvailabilityIsTrue()).thenReturn(mockVehiclesList);
        List<Vehicle> resultVehicleList = vehicleService.getAllAvailableCars();
        assertEquals(mockVehiclesList, resultVehicleList);
    }

    @Test
    void setCarNotAvailableTest() {
        int vehicleId = 1;

        vehicleService.setCarNotAvailable(vehicleId);
        verify(vehicleRepo, times(1)).setAvailabilityToFalse(vehicleId);
    }

    @Test
    void setCarAvailableTest() {
        int vehicleId = 1;

        vehicleService.setCarAvailable(vehicleId);
        verify(vehicleRepo, times(1)).setAvailabilityToTrue(vehicleId);
    }
}

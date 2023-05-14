package pl.messages.web.dao;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pl.messages.web.model.Vehicle;

import java.util.List;

@Component
public class VehicleDAO {
    private final JdbcTemplate jdbcTemplate;

    public VehicleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Vehicle> index() {
        return jdbcTemplate.query("SELECT * FROM vehicles", new BeanPropertyRowMapper<>(Vehicle.class));
    }

    public void addNewCar(Vehicle vehicle) {
        jdbcTemplate.update("INSERT INTO vehicles (car_brand, car_year, price_rent)VALUES(?, ?, ?)", vehicle.getCAR_BRAND(),
                vehicle.getCAR_YEAR(), vehicle.getPRICE_RENT()); // here the problem
    }
}

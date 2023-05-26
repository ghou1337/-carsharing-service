package pl.messages.web.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.messages.web.model.Vehicle;

import java.time.Period;
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

    public void userVehicle(int id) {
        jdbcTemplate.update("UPDATE users SET rented_car_id = ? WHERE id = ?", id, 1);
    }


    public void addNewCar(Vehicle vehicle) {
        jdbcTemplate.update("INSERT INTO vehicles (car_brand, car_year, price_rent)VALUES(?, ?, ?)", vehicle.getCAR_BRAND(),
                vehicle.getCAR_YEAR(), vehicle.getPRICE_RENT()); // here the problem
    }

    public List<Vehicle> rentVehicle() {
        return jdbcTemplate.query("SELECT vehicles.car_brand FROM vehicles " +
                        "JOIN users ON vehicles.id = users.rented_car_id WHERE users.id = 1",
                new BeanPropertyRowMapper<>(Vehicle.class));
    }
}

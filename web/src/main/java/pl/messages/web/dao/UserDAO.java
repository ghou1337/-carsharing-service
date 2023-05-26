package pl.messages.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.messages.web.model.User;
import pl.messages.web.model.Vehicle;

import java.util.List;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> index() {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = 1", new BeanPropertyRowMapper<>(User.class));
    }

    public List<Vehicle> rentVehicle() {
        return jdbcTemplate.query("SELECT vehicles.car_brand FROM vehicles " +
                "JOIN users ON vehicles.id = users.rented_car_id WHERE users.id = 1",
                new BeanPropertyRowMapper<>(Vehicle.class));
    }
}
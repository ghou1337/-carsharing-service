package pl.messages.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import pl.messages.web.model.User;
import pl.messages.web.model.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findIntoTable(String login, String password) {
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
        RowMapper<User> rowMapper = new UserRowMapper();
        List<User> users = jdbcTemplate.query(sql, rowMapper, login, password);
        return users.isEmpty() ? null : users.get(0);
    }

    public User currentUser() {
        RowMapper<User> rowMapper = new UserRowMapper();
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id = 1",  new BeanPropertyRowMapper<>(User.class));
        return  users.get(0);
    }
    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            return user;
        }
    }

}

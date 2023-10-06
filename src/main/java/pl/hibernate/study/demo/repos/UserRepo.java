package pl.hibernate.study.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hibernate.study.demo.model.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String u);
}

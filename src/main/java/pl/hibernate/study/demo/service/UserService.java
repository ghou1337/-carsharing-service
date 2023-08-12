package pl.hibernate.study.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.UserRepo;
import pl.hibernate.study.demo.repos.VehicleRepo;
import pl.hibernate.study.demo.security.config.UserDetailsConfig;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final VehicleRepo vehicleRepo;

    public UserService(UserRepo userRepo, VehicleRepo vehicleRepo) {
        this.userRepo = userRepo;
        this.vehicleRepo = vehicleRepo;
    }

    public List<User> findAllUsers() {
        return  userRepo.findAll();
    }

    public User findUserById(int id) {
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public Optional<User> findUserByLogin(String username) {
        Optional<User> user = userRepo.findByLogin(username);
        return user;
    }

    public void register(User user){
        user.setPermissions("USER");
        userRepo.save(user);
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsConfig userDetailsConfig = (UserDetailsConfig)authentication.getPrincipal();
        return findUserById(userDetailsConfig.getUser().getId());
    }
}

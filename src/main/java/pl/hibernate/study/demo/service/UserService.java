package pl.hibernate.study.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.model.Vehicle;
import pl.hibernate.study.demo.repos.UserRepo;
import pl.hibernate.study.demo.repos.VehicleRepo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VehicleRepo vehicleRepo;
    public List<User> findAllUsers() {
        return  userRepo.findAll();
    }

    public User findUserById(int id) {
        Optional<User> usergaga = userRepo.findById(id);
        return usergaga.get();
    }

    public Vehicle getUserCar(int userId, int carId) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(carId);
        return vehicle.get();
    }

//    public Vehicle getUserCarBrand(User user) {
//        userRepo.findByRentedVehicle(user);
//    }
}

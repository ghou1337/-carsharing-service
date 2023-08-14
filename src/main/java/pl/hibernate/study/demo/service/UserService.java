package pl.hibernate.study.demo.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.repos.UserRepo;
import pl.hibernate.study.demo.security.UserDetailsConfig;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    public User findUserById(int id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
    }

    public Optional<User> findUserByLogin(String username) {
        return userRepo.findByLogin(username);
    }

    public void register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPermissions("USER");
        userRepo.save(user);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsConfig userDetailsConfig = (UserDetailsConfig)authentication.getPrincipal();
        return findUserById(userDetailsConfig.user().getId());
    }
}

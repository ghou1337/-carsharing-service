package pl.hibernate.study.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.repos.UserRepo;
import pl.hibernate.study.demo.security.config.UserDetailsConfig;

import java.util.Optional;
@Service
public class UserDetailsServiceRealisation implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user  = userRepo.findByLogin(username);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new UserDetailsConfig(user.get());
    }
}

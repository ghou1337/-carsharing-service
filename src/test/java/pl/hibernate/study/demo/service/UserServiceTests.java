package pl.hibernate.study.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.hibernate.study.demo.model.User;
import pl.hibernate.study.demo.repos.UserRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User(0, "user", "password", "user@email.com", "Bob", 1000, "USER");
    }

    @Test
    void findUserByIdTest() {
        Mockito.when(userRepo.findById(0)).thenReturn(Optional.of(mockUser));
        User result = userService.findUserById(0);
        verify(userRepo).findById(0);
        assertEquals(mockUser, result);
    }

    @Test
    void registerNewUserTest() {
        String encodedPassword = passwordEncoder.encode(mockUser.getPassword());
        userService.registerNewUser(mockUser);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepo).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();
        assertEquals(0, capturedUser.getId());
        assertEquals("user", capturedUser.getUsername());
        assertEquals(encodedPassword, capturedUser.getPassword());
        assertEquals("USER", capturedUser.getRole());
    }
}

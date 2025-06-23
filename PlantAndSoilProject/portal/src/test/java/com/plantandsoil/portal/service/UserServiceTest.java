package com.plantandsoil.portal.service;

import com.plantandsoil.portal.model.User;
import com.plantandsoil.portal.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");
        user.setPassword("password123");

        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertTrue(new BCryptPasswordEncoder().matches("password123", savedUser.getPassword()));
        assertTrue(savedUser.getTermsAccepted());
    }

    @Test
    public void testLoadUserByUsername_UserExists() {
    User user = new User();
    user.setUsername("testuser");
    user.setPassword("encodedpass");

    when(userRepository.findByUsername("testuser")).thenReturn(user);

    var userDetails = userService.loadUserByUsername("testuser");

    assertEquals("testuser", userDetails.getUsername());
    assertEquals("encodedpass", userDetails.getPassword());
}
}

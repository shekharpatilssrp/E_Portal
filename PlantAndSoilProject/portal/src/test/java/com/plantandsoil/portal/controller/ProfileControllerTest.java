package com.plantandsoil.portal.controller;


import com.plantandsoil.portal.model.User;
import com.plantandsoil.portal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    @WithMockUser(username = "john")
    public void testChangePasswordSuccess() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setPassword("encodedOldPassword");

        when(userRepository.findByUsername("john")).thenReturn(user);
        when(passwordEncoder.matches("oldpass", "encodedOldPassword")).thenReturn(true);
        when(passwordEncoder.encode("newpass")).thenReturn("encodedNewPassword");

        mockMvc.perform(post("/changepassword")
                .param("currentPassword", "oldpass")
                .param("newPassword", "newpass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/changepassword"))
                .andExpect(flash().attribute("msg", "Password updated"));

        verify(userRepository).save(user);
    }
}

package com.plantandsoil.portal.controller;


import com.plantandsoil.portal.model.User;
import com.plantandsoil.portal.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testShowIndexPage() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testShowPlantPage() throws Exception {
        mockMvc.perform(get("/plant"))
                .andExpect(status().isOk())
                .andExpect(view().name("plant"));
    }

    @Test
    void testShowSoilPage() throws Exception {
        mockMvc.perform(get("/soil"))
                .andExpect(status().isOk())
                .andExpect(view().name("soil"));
    }

    @Test
    void testShowSoilTestPage() throws Exception {
        mockMvc.perform(get("/soiltest"))
                .andExpect(status().isOk())
                .andExpect(view().name("soiltest"));
    }

    @Test
    void testShowAboutPage() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(view().name("about"));
    }

    @Test
    void testShowContactPage() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("contact"));
    }

    @Test
    void testShowLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testShowRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testCreateUserSuccess() throws Exception {
        // Arrange
        when(userService.checkByEmail("test@example.com")).thenReturn(false);
        when(userService.checkByUsername("testuser")).thenReturn(false);
        when(userService.registerUser(any(User.class))).thenReturn(new User());

        // Act & Assert
        mockMvc.perform(post("/createUser")
                .param("username", "testuser")
                .param("email", "test@example.com")
                .param("password", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register"));
    }
}

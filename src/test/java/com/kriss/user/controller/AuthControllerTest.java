package com.kriss.user.controller;

import com.kriss.user.entity.Users;
import com.kriss.user.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    // ✅ TEST REGISTER
    @Test
    void register_should_return_200_with_user_info() throws Exception {

        when(userService.register("test@email.com", "motdepasse123"))
                .thenReturn(Users.builder().id(1L).email("test@email.com").build());

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "test@email.com",
                                    "password": "motdepasse123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.message").value("Inscription réussie"));
    }

    // ✅ TEST LOGIN SUCCESS
    @Test
    void login_should_return_200_with_user_info() throws Exception {

        when(userService.login("test@email.com", "motdepasse123"))
                .thenReturn(Users.builder().id(1L).email("test@email.com").build());

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "test@email.com",
                                    "password": "motdepasse123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.message").value("Connexion réussie"));
    }

    // ❌ USER NOT FOUND
    @Test
    void login_should_return_401_when_user_not_found() throws Exception {

        when(userService.login("inconnu@email.com", "motdepasse123"))
                .thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "inconnu@email.com",
                                    "password": "motdepasse123"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    // ❌ WRONG PASSWORD
    @Test
    void login_should_return_401_when_invalid_password() throws Exception {

        when(userService.login("test@email.com", "mauvaismdp"))
                .thenThrow(new RuntimeException("Invalid password"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "test@email.com",
                                    "password": "mauvaismdp"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }
}

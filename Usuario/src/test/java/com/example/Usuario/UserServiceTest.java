package com.example.Usuario;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Repositories.CityRepository;
import com.example.Usuario.Repositories.UserRepository;
import com.example.Usuario.Services.CityService;
import com.example.Usuario.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchAll() {
        // Configurar el estado necesario para la prueba
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // Ejecutar el método que se va a probar
        ArrayList<User> result = userService.SearchAll();

        // Verificar el resultado
        assertEquals(users, result);
    }

    @Test
    public void testPut() {
        // Configurar el estado necesario para la prueba
        User user = new User();

        // Ejecutar el método que se va a probar
        userService.Put(user);

        // Verificar el comportamiento esperado
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        // Configurar el estado necesario para la prueba
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // Ejecutar el método que se va a probar
        userService.UpdateUser(user);

        // Verificar el comportamiento esperado
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        // Configurar el estado necesario para la prueba
        Integer userId = 1;

        // Ejecutar el método que se va a probar
        userService.DeleteUser(userId);

        // Verificar el comportamiento esperado
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testFindById_ExistingUser() {
        // Configurar el estado necesario para la prueba
        Integer userId = 1;
        User user = new User();
        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.getReferenceById(userId)).thenReturn(user);

        // Ejecutar el método que se va a probar
        User result = userService.findOne(userId);

        // Verificar el resultado
        assertEquals(user, result);
    }

    @Test
    public void testFindById_NonExistingCity() {
        // Configurar el estado necesario para la prueba
        Integer userId = 1;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Ejecutar el método que se va a probar
        User result = userService.findOne(userId);

        // Verificar el resultado
        assertEquals(null, result);
    }

    @Test
    public void testFindById_NonExistingUser() {
        // Configurar el estado necesario para la prueba
        Integer userId = 1;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Ejecutar el método que se va a probar
        User result = userService.findOne(userId);

        // Verificar el resultado
        assertEquals(null, result);
    }
}

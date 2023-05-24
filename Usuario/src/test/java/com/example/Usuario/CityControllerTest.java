package com.example.Usuario;

import com.example.Usuario.Controllers.CityController;
import com.example.Usuario.Entities.City;
import com.example.Usuario.Services.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CityControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        // Arrange
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        when(cityService.SearchAll()).thenReturn(cities);

        // Act
        ArrayList<City> result = cityController.getAll();

        // Assert
        verify(cityService, times(1)).SearchAll();
        // Add additional assertions based on your requirements
    }

    @Test
    void testNewCity() {
        // Arrange
        City city = new City();

        // Act
        cityController.NewCity(city);

        // Assert
        verify(cityService, times(1)).Put(city);
        // Add additional assertions based on your requirements
    }

    @Test
    void testUpdateCity() {
        // Arrange
        Integer id = 1;
        String name = "Updated City";
        City existingCity = new City();
        when(cityService.findById(id)).thenReturn(existingCity);

        // Act
        cityController.updateCity(name, id);

        // Assert
        verify(cityService, times(1)).findById(id);
        verify(cityService, times(1)).updateCity(existingCity);
        // Add additional assertions based on your requirements
    }

    @Test
    void testUpdateCity_NonExistingCity() {
        // Arrange
        Integer id = 1;
        String name = "Updated City";
        when(cityService.findById(id)).thenReturn(null);

        // Act
        cityController.updateCity(name, id);

        // Assert
        verify(cityService, times(1)).findById(id);
        verify(cityService, never()).updateCity(any());
        // Add additional assertions based on your requirements
    }

    @Test
    void testDeleteCity() {
        // Arrange
        Integer id = 1;

        // Act
        cityController.deleteCity(id);

        // Assert
        verify(cityService, times(1)).deleteCity(id);
        // Add additional assertions based on your requirements
    }
}

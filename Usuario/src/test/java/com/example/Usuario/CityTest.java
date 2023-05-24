package com.example.Usuario;

import com.example.Usuario.Entities.City;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CityTest {
    @Test
    void testCityId() {
        // Arrange
        Integer cityId = 1;
        City city = new City();
        city.setCity_id(cityId);

        // Act
        Integer result = city.getCity_id();

        // Assert
        assertEquals(cityId, result);
    }

    @Test
    void testName() {
        // Arrange
        String cityName = "New York";
        City city = new City();
        city.setName(cityName);

        // Act
        String result = city.getName();

        // Assert
        assertEquals(cityName, result);
    }
}

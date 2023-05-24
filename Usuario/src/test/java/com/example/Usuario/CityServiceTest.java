package com.example.Usuario;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Repositories.CityRepository;
import com.example.Usuario.Services.CityService;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchAll() {
        // Configurar el estado necesario para la prueba
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        when(cityRepository.findAll()).thenReturn(cities);

        // Ejecutar el método que se va a probar
        ArrayList<City> result = cityService.SearchAll();

        // Verificar el resultado
        assertEquals(cities, result);
    }

    @Test
    public void testPut() {
        // Configurar el estado necesario para la prueba
        City city = new City();

        // Ejecutar el método que se va a probar
        cityService.Put(city);

        // Verificar el comportamiento esperado
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    public void testUpdateCity() {
        // Configurar el estado necesario para la prueba
        City city = new City();
        when(cityRepository.save(city)).thenReturn(city);

        // Ejecutar el método que se va a probar
        cityService.updateCity(city);

        // Verificar el comportamiento esperado
        verify(cityRepository, times(1)).save(city);
    }

    @Test
    public void testDeleteCity() {
        // Configurar el estado necesario para la prueba
        Integer cityId = 1;

        // Ejecutar el método que se va a probar
        cityService.deleteCity(cityId);

        // Verificar el comportamiento esperado
        verify(cityRepository, times(1)).deleteById(cityId);
    }

    @Test
    public void testFindById_ExistingCity() {
        // Configurar el estado necesario para la prueba
        Integer cityId = 1;
        City city = new City();
        when(cityRepository.existsById(cityId)).thenReturn(true);
        when(cityRepository.getReferenceById(cityId)).thenReturn(city);

        // Ejecutar el método que se va a probar
        City result = cityService.findById(cityId);

        // Verificar el resultado
        assertEquals(city, result);
    }

    @Test
    public void testFindById_NonExistingCity() {
        // Configurar el estado necesario para la prueba
        Integer cityId = 1;
        when(cityRepository.existsById(cityId)).thenReturn(false);

        // Ejecutar el método que se va a probar
        City result = cityService.findById(cityId);

        // Verificar el resultado
        assertEquals(null, result);
    }
}

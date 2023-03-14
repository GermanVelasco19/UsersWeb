package com.example.Usuario.Controllers;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CityController {

    @Autowired
    CityService city;

    @GetMapping(value="/allcities", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<City> getAll(){
        return city.SearchAll();
    }


    @PostMapping(value="/NewCity")
    public void NewCity(@RequestBody City city1){
        city.Put(city1);
    }

    @PutMapping(value="/updateCity/{id}")
    public void updateCity(@RequestBody String name,@PathVariable Integer id){
        //city.updateCity(name,id);
    }

    @DeleteMapping(value="/deleteCity/{ID}")
    public void deleteCity(@PathVariable Integer ID){
        city.deleteCity(ID);
    }
}

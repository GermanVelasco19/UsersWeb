package com.example.Usuario.Services;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CityService {

    @Autowired
    CityRepository Repo;

    public ArrayList<City> SearchAll(){
        return (ArrayList<City>) Repo.findAll();
    }

    public void Put(City c){
        Repo.save(c);
    }

    public void updateCity(City c){
        Repo.save(c);
    }

    public void deleteCity(Integer ID){
        Repo.deleteById(ID);
    }

    public City findById(Integer ID){
        if (Repo.existsById(ID)){
            return Repo.getReferenceById(ID);
        }else{
            return null;
        }
    }
}

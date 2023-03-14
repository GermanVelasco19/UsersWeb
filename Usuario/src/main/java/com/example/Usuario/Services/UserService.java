package com.example.Usuario.Services;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    UserRepository Repo;

    public ArrayList<User> SearchAll(){
        return (ArrayList<User>) Repo.findAll();
    }

    public void Put (User u ){
        Repo.save(u);
    }

    public void Update (Integer id, String name, String last_name, Date birthdate, City city){
        Repo.updateUser(id,name,last_name,birthdate,city);
    }

    public void InsertNewUser(User u){
        Repo.save(u);
    }

    public void DeleteUser(Integer id){
        Repo.deleteById(id);
    }
}

package com.example.Usuario.Controllers;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService user;

    @GetMapping(value = "/get_users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<User> getAll() {return user.SearchAll();}

    @PostMapping(value = "/NewUser")
    public void NewUser(@RequestBody User u){

        user.Put(u);

    }

    @PutMapping(value = "/updateUser/{id}")
    public void updateUser(@RequestBody User u, @PathVariable Integer id){
        u.setUser_id(id);
        User x =user.findOne(id);
        if (x!=null) {
            x.setName_user(u.getName_user());
            x.setLast_name_user(u.getLast_name_user());
            x.setBirthdate_user(u.getBirthdate_user());
            x.setCity_id(u.getCity_id());

            user.UpdateUser(u);
        }else{
            System.out.println("Ese Usuario no existe");
        }
    }
    @DeleteMapping(value = "/deleteUser/{id}")
    public void DeleteUser (@PathVariable Integer id){
        user.DeleteUser(id);
    }
}

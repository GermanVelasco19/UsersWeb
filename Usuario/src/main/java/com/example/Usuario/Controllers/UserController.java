package com.example.Usuario.Controllers;

import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {

    @Autowired
    UserService user;

    @GetMapping(value = "/get_users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<User> getAll() {return user.SearchAll();}

    @PostMapping(value = "/NewUser")
    public void NewUser(@RequestBody User u){
        user.InsertNewUser(u);
    }

    @PutMapping(value = "/updateUser/{id}")
    public void updateUser(@RequestBody User user, @PathVariable Integer id){
        //user.Update(user.getUser_id(),user.getName_user(),user.getLast_name_user(),user.getBirthdate_user(),user.getCity_id());
    }
    @DeleteMapping(value = "/deleteUser/{id}")
    public void DeleteUser (@PathVariable Integer id){
        user.DeleteUser(id);
    }
}

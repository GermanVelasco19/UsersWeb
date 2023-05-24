package com.example.Usuario.Controllers;

import com.example.Usuario.Entities.User;
import com.example.Usuario.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService user;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/get_users", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ArrayList<User> getAll() {
        ArrayList<User> allUsers = user.SearchAll();
        ArrayList<User> activeUsers = new ArrayList<>();

        for (User u : allUsers) {
            if (u.isActivo()) {
                activeUsers.add(u);
            }
        }

        return activeUsers;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/get_users_paginated")
    public ResponseEntity<Page<User>>usersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "id") String order,
            @RequestParam(defaultValue = "true") boolean asc
    ){
        Page<User> userspages = user.paginateUser(PageRequest.of(page,size, Sort.by(order)));
        if(!asc){
            userspages = user.paginateUser(PageRequest.of(page,size, Sort.by(order).descending()));
        }
        return new ResponseEntity<Page<User>>(userspages, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/NewUser")
    public boolean NewUser(@RequestBody User u){
        ArrayList<User> users = user.SearchAll();

        boolean userExists = users.stream()
                .anyMatch(existingUser -> existingUser.getUsername().equals(u.getUsername()));

        if (userExists) {
            return false;
        } else {
            user.Put(u);
            return true;
        }

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("user")
    @ResponseBody
    public String login(@RequestParam("user") String username,@RequestParam("password") String pwd){
        ArrayList<User> users = user.SearchAll();
        boolean correcto = false;
        for (int i = 0;i<users.size();i++) {
            if (users.get(i).getUsername().equals(username)){
                if(users.get(i).getPassword().equals(pwd) ){
                    correcto=true;
                }
            }
        }
        if(correcto) {
            String token = getJWTToken(username);
            User user = new User();
            user.setToken(token);
            return "{\"token\":\"" + token + "\"}";
        }else{
            return "{\"token\":\"" + "error" + "\"}";
        }
    }


    private String getJWTToken(String username){
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils.
                commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts.
                builder().
                setId("JaverianaJWT")
                .setSubject(username)
                .claim("authorities",grantedAuthorityList.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date((System.currentTimeMillis()+600000)))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer"+token;
    }

    @PutMapping(value = "/updateUser/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public void updateUser(@RequestBody User u, @PathVariable Integer id){
        u.setUser_id(id);
        User x =user.findOne(id);
        System.out.println(x);
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

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/deleteUser/{id}")
    public void DeleteUser (@PathVariable Integer id){

        User userToDelete = user.findOne(id);
        if (userToDelete != null) {
            userToDelete.setActivo(false);
            user.UpdateUser(userToDelete);
        }

    }
}

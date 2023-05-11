package com.example.Usuario.Services;

import com.example.Usuario.Entities.User;
import com.example.Usuario.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


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
    public void UpdateUser (User u){
        System.out.println(u.getName_user());

        Repo.save(u);
    }

    public Page<User> paginateUser(Pageable pageable){
        return Repo.findAll(pageable);
    }

    public User findOne(Integer id){
        if (Repo.existsById(id)){
            return Repo.getReferenceById(id);
        }else {
            return null;
        }
    }

    public void DeleteUser(Integer id){
        Repo.deleteById(id);
    }
}

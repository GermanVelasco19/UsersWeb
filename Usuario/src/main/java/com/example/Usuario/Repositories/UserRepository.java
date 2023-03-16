package com.example.Usuario.Repositories;


import com.example.Usuario.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface UserRepository extends JpaRepository<User,Integer> {

}

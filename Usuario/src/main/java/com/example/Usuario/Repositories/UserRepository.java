package com.example.Usuario.Repositories;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Entities.User;
import jakarta.persistence.QueryHint;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends JpaRepository<User,Integer> {

}

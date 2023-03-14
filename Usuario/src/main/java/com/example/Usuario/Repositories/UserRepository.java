package com.example.Usuario.Repositories;

import com.example.Usuario.Entities.City;
import com.example.Usuario.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("UPDATE User u SET\n" +
            "  u.name_user =: name,\n" +
            "  u.last_name_user =: last_name,\n" +
            "  u.birthdate_user =: birthdate,\n" +
            "  u.city_id =: city WHERE u.user_id =: id")
    void updateUser(@Param("id") Integer id, @Param("name") String name, @Param("last_name") String last_name, @Param("birthdate")Date birthdate, @Param("city")City city);
}

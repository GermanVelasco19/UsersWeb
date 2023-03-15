package com.example.Usuario.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer city_id;

    private String name;

    @OneToMany(mappedBy = "user_id")
    @JsonIgnore
    private List<User> users;

}

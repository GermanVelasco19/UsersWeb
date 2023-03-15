package com.example.Usuario.Entities;

import jakarta.persistence.*;
import lombok.Data;
    import lombok.Value;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    private String name_user;
    private String last_name_user;

    private String birthdate_user;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city_id;

}

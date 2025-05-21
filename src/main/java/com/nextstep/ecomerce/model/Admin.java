package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Component
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private final String role="ADMIN";

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
    @JsonManagedReference("adminProduct")
    List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonManagedReference("adminImage")
    List<Image> images;
}

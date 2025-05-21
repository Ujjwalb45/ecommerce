package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private int stock;
    private String imageUrl;

    @ManyToOne
    @JsonBackReference("userProduct")
    Users users;


    @ManyToOne
    @JoinColumn(name="admin_id")
    @JsonBackReference("adminProduct")
    Admin admin;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonManagedReference("productImage")
    List<Image> images;

}

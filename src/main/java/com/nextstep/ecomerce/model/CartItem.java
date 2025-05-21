package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private String name;
    private int quantity;
    private double price;
    private String image;

    @ManyToOne
    @JsonBackReference("userCartItem")
    @JoinColumn(name = "user_id")
    Users users;

}

package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email, password;
    private final String role="USER";

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonManagedReference("userProduct")
    List<Product> productList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonManagedReference("userCartItem")
    List<CartItem> cartItemList;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference("checkout")
    List<Checkout> checkoutList;

}

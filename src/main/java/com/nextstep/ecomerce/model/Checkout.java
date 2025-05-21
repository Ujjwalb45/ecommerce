package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int transactionId;
    private String totalPrice;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String status;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference("checkout")
    Users user;

}

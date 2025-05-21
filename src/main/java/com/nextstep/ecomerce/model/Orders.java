package com.nextstep.ecomerce.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private String status;
    private  LocalDateTime createdAt= LocalDateTime.now();

    @ManyToOne
    private Users users;
}

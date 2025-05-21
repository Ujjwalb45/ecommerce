package com.nextstep.ecomerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String  imageUrl;
    private String imageType;
    private String publicId;

    @ManyToOne
    @JoinColumn(name="product_id")
    @JsonBackReference("productImage")
    Product product;

    @ManyToOne
    @JoinColumn(name="admin_id")
    @JsonBackReference("adminImage")
    Admin admin;



}

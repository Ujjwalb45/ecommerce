package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Product;
import com.nextstep.ecomerce.repo.ApiResponse;
import com.nextstep.ecomerce.repository.ProductRepository;
import com.nextstep.ecomerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DisplayProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/displayProducts")
    ResponseEntity<ApiResponse> displayProducts(){
        List<Product> productList=productRepository.findAll();
        ApiResponse apiResponse=ApiResponse.<Product>builder().statusCode(HttpStatus.OK.value()).usersList(productList).build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

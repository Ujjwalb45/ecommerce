package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Product;
import com.nextstep.ecomerce.repo.ApiResponse;
import com.nextstep.ecomerce.repository.ProductRepository;
import com.nextstep.ecomerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin") // Allow requests from React frontend
public class AdminController {

    @Autowired
    AdminService adminService;




    @PostMapping("/addProducts")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> addProducts(@RequestBody  Product product){
        adminService.addProducts(product);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Products Added Successfully").build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/displayProducts")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> displayProducts(){
        List<Product> productList=adminService.getProductS();
        ApiResponse apiResponse=ApiResponse.<Product>builder().statusCode(HttpStatus.OK.value()).usersList(productList).build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @DeleteMapping("/deleteProduct/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> deleteProduct(@PathVariable("id") Long id){
        System.out.println("Id is : "+ id);
        adminService.deleteProductById(id);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Deleted Successfully").build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PatchMapping("/updateProduct/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        adminService.editProductById(id,product);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Edited Successfully Successfully").build();
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}

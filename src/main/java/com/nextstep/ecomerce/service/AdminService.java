package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.model.Product;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AdminService {
    void registerAdmin(Admin admin);


    void addProducts(Product product);
    List<Product> getProductS();
    void deleteProductById(Long id);
    void editProductById(Long id,Product product);


}

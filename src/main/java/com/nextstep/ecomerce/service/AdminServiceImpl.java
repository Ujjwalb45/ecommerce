package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.model.Image;
import com.nextstep.ecomerce.model.Product;
import com.nextstep.ecomerce.repository.AdminRepository;
import com.nextstep.ecomerce.repository.ImageRepository;
import com.nextstep.ecomerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Admin admin;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ImageRepository imageRepository;


    @Override
    public void registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);

    }

    @Override
    public void addProducts(Product product) {

        //Image image=imageRepository.findById(id).orElse(null);

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Admin admin=adminRepository.findByUsername(authentication.getName());


        product.setAdmin(admin);


//    List<Image>lists=new ArrayList<>();
//
//    lists.add(image);
//
// for(Image images:lists){
//     images.setProduct(product);
// }
        if(admin.getProducts()==null){
            admin.setProducts(new ArrayList<>());
        }
        admin.getProducts().add(product);


      //  productRepository.save(product);
        adminRepository.save(admin);
    }


    @Override
    public List<Product> getProductS() {
       List<Product> productList=productRepository.findAll();
       return  productList.isEmpty()? new ArrayList<>():productList;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);

    }

    @Override
    public void editProductById(Long id, Product product) {
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product1= optionalProduct.get();
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
            product1.setStock(product.getStock());
            product1.setDescription(product.getDescription());
            product1.setImages(product1.getImages());

            productRepository.save(product1);

        }


    }
}

package com.nextstep.ecomerce.repository;

import com.nextstep.ecomerce.model.CartItem;
import com.nextstep.ecomerce.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    List<CartItem> findByProductId(int productId);
    List<CartItem> findByUsers_Id(Long id);
    List<CartItem> findByUsers(Users user);
}

package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.CartItem;

import java.util.ArrayList;
import java.util.List;

import com.nextstep.ecomerce.model.Users;
import com.nextstep.ecomerce.repository.CartItemRepository;
import com.nextstep.ecomerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    UsersRepository usersRepository;


    @Override
    public List<CartItem> getCartItems() {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Users user=usersRepository.findByEmail(authentication.getName());

List<CartItem> cartItems =cartItemRepository.findByUsers(user);
       // List<CartItem> cartItems=cartItemRepository.findByUsers_Id(user.getId());
        return  cartItems;
    }

    @Override
    public CartItem addToCart(CartItem cartItem) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Users user=usersRepository.findByEmail(authentication.getName());

        cartItem.setUsers(user);

        if(user.getCartItemList()==null){
            user.setCartItemList(new ArrayList<>());
        }
        user.getCartItemList().add(cartItem);


         usersRepository.save(user);
         return  cartItem;
    }



    @Override
    public void removeFromCart(int id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
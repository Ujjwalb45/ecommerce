package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.CartItem;

import java.util.List;

public interface CartService {
    List<CartItem> getCartItems();
    CartItem addToCart(CartItem cartItem);
    void removeFromCart(int id);
    void clearCart();
}

package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.CartItem;
import com.nextstep.ecomerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000") // Allow React to access API
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/items")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<CartItem> getCartItems() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        List<CartItem> cartItems= cartService.getCartItems();
        return  cartItems;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('USER')")
    public CartItem addToCart(@RequestBody CartItem cartItem) {
        return cartService.addToCart(cartItem);
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public void removeFromCart(@PathVariable int id) {
        cartService.removeFromCart(id);
    }

    @DeleteMapping("/clear")
    @PreAuthorize("hasAnyAuthority('USER')")
    public void clearCart() {
        cartService.clearCart();
    }
}

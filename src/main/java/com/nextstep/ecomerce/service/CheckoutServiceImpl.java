package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Checkout;
import com.nextstep.ecomerce.model.Users;
import com.nextstep.ecomerce.repository.CheckoutRepository;
import com.nextstep.ecomerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CheckoutServiceImpl implements  CheckoutService {

    @Autowired
    CheckoutRepository checkoutRepository;
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Checkout saveCheckout(Checkout checkout) {




        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Users user=usersRepository.findByEmail(authentication.getName());

        checkout.setUser(user);

        checkout.setTransactionId(getRandom());
        user.getCheckoutList().add(checkout);
        Checkout checkout1=checkoutRepository.save(checkout);
         usersRepository.save(user);


        return checkout1;
    }

    public  Integer getRandom(){
        Random random=new Random();

        return  random.nextInt(100,900);
    }

    @Override
    public Checkout getByTransactionId(int id) {
        return checkoutRepository.findByTransactionId(id);
    }
}

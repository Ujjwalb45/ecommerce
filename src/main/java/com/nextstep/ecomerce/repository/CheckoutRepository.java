package com.nextstep.ecomerce.repository;

import com.nextstep.ecomerce.model.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    Checkout findByTransactionId(int id);
}

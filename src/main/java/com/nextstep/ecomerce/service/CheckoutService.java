package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Checkout;

public interface CheckoutService {
    public Checkout saveCheckout(Checkout checkout);
    public Checkout getByTransactionId(int id);

}

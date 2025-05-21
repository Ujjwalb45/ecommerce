package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Checkout;
import com.nextstep.ecomerce.service.CheckoutService;
import org.hibernate.engine.internal.CacheHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    private final String KHALTI_SECRET_KEY = "test_secret_key_dc74e73949f342b68d9ad0e2042150f0";


//    @PostMapping("/create-payment-token")
//    @PreAuthorize("hasAnyAuthority('USER')")
//   public ResponseEntity<?> initiatePayment(@RequestBody Map<String,Object> payload){
//        String totalPrice=(String) payload.get("totalPrice");
//        String name=(String) payload.get("name");
//        String address=(String)payload.get("address");
//        String phone=(String) payload.get("phone");
//        String email=(String) payload.get("email");
//
//
//        Checkout checkout=Checkout.builder()
//                .name(name)
//                .totalPrice(totalPrice)
//                .phone(phone)
//                .email(email)
//                .status("INITIATED")
//                .build();
//      Checkout checkout1=  checkoutService.saveCheckout(checkout);
//        Map<String,Object> response=new HashMap<>();
//        response.put("KhaltiPaymentUrl","https://khalti.com/#/test-payment");
//        response.put("transactionId",checkout1.getTransactionId());
//        return ResponseEntity.ok(response);
//   }

    @PostMapping("/initiate-esewa")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity<?> initiateEsewa(@RequestBody Map<String, Object> payload) {
        try {
            String totalPrice = String.valueOf(payload.get("totalPrice")) ;
            String name = (String) payload.get("name");
            String address = (String) payload.get("address");
            String phone = (String) payload.get("phone");
            String email = (String) payload.get("email");

            // Validate data
            if (totalPrice == null || name == null || address == null || phone == null || email == null) {
                throw new IllegalArgumentException("Missing required data");
            }

            Checkout checkout = Checkout.builder()
                    .name(name)
                    .totalPrice(totalPrice)
                    .phone(phone)
                    .email(email)
                    .status("INITIATED")
                    .build();

            Checkout savedCheckout = checkoutService.saveCheckout(checkout);
            String transactionId = String.valueOf(savedCheckout.getTransactionId());

            // Generate eSewa redirect URL
            String esewaUrl = "https://rc-epay.esewa.com.np/api/epay/main/v2/form";
            String successUrl = "http://localhost:3000/payment-success";
            String failureUrl = "http://localhost:3000/payment-failed";

            String redirectUrl = esewaUrl + "?" +
                    "amount=" + totalPrice +
                    "&tax_amount=0" +
                    "&total_amount=" + totalPrice +
                    "&transaction_uuid=" + transactionId +
                    "&product_code=EPAYTEST" +
                    "&product_service_charge=0" +
                    "&product_delivery_charge=0" +
                    "&success_url=" + successUrl +
                    "&failure_url=" + failureUrl;

            Map<String, Object> response = new HashMap<>();
            response.put("esewaRedirectUrl", redirectUrl);
            response.put("transactionId", transactionId);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();  // Log the stack trace to debug
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the payment request: " + e.getMessage());
        }
    }


    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String,String> payload){
        String token=payload.get("token");
        String amount=payload.get("amount");
       RestTemplate restTemplate = new RestTemplate();
       HttpHeaders headers = new HttpHeaders();
       headers.set("Authorization", "Key " + KHALTI_SECRET_KEY);
       headers.setContentType(MediaType.APPLICATION_JSON);

       Map<String, Object> body = new HashMap<>();
       body.put("token", token);
       body.put("amount", amount);

       HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

       try {
           ResponseEntity<String> response = restTemplate.postForEntity(
                   "https://a.khalti.com/api/v2/payment/verify/", request, String.class);

           if (response.getStatusCode() == HttpStatus.OK) {
               return ResponseEntity.ok("Payment verified successfully");
           }
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification failed");
       }

       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
   }

}


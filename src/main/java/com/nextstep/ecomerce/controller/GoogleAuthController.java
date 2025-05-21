//package com.nextstep.ecomerce.controller;
//
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class GoogleAuthController {
//    @GetMapping("/google")
//    public String googleLogin() {
//        // Redirects to Google login (Spring Security handles this automatically)
//        return "Redirecting to Google...";
//    }
//
//    @GetMapping("/auth/google/callback")
//    public String googleCallback(@RequestParam String code, OAuth2AuthenticationToken authentication) {
//        // Once Google redirects here, you can get the user profile info
//        OAuth2User principal = authentication.getPrincipal();
//        String username = principal.getAttribute("login");  // Or other attributes
//        System.out.println("User Name is: "+ username);
//
//        // Optionally, create a session or generate a JWT token
//        // Save the user info and return a response (e.g., a JWT or user session)
//        return "Logged in with Google: " + username;
//    }
//}

package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.repo.ApiResponse;
import com.nextstep.ecomerce.service.AdminService;
import com.nextstep.ecomerce.service.CustomUserDetailsService;
import com.nextstep.ecomerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/registerAdmin")
    ResponseEntity<ApiResponse> registerAdmin(@RequestBody Admin admin ){
        adminService.registerAdmin(admin);
        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Admin Registered").build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credential");
        }
        UserDetails userDetails =customUserDetailsService.loadUserByUsername(admin.getUsername());
        String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        System.out.println("Role is: "+ role);

        String token = jwtUtil.generateToken(userDetails.getUsername(), role);

        return ResponseEntity.ok(token);
    }
}

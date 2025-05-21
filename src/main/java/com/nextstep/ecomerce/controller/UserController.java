package com.nextstep.ecomerce.controller;

import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.model.Users;
import com.nextstep.ecomerce.repo.ApiResponse;
import com.nextstep.ecomerce.repository.UsersRepository;
import com.nextstep.ecomerce.service.CustomUserDetailsService;
import com.nextstep.ecomerce.service.UserService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/registerUser")
    ResponseEntity<ApiResponse> saveUsers(@RequestBody Users users){
        try{
            userService.saveUser(users);
            ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Saved Successfully").build();
            return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        }
        catch (Exception ex){
            ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(ex.toString()).build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PostMapping("/loginUser")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody Users users) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword())
            );
        } catch (BadCredentialsException e) {
            ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message("Invalid Credentials").build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
        UserDetails userDetails =customUserDetailsService.loadUserByUsername(users.getEmail());
        String role = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("USER");

        System.out.println("Role is: "+ role);

        String token = jwtUtil.generateToken(userDetails.getUsername(), role);
        Users user1=usersRepository.findByEmail(users.getEmail());


        ApiResponse apiResponse=ApiResponse.builder().statusCode(HttpStatus.OK.value()).message("Success").token(token).userId(String.valueOf(user1.getId())).build();

        System.out.println("TOKEN "+ token);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }



}

package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Users;
import com.nextstep.ecomerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void saveUser(Users users) {
        System.out.println("email is: " + users.getEmail());



            users.setPassword(passwordEncoder.encode(users.getPassword()));
            usersRepository.save(users);


    }


}

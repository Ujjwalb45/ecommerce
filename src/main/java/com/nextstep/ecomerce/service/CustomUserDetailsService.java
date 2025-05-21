package com.nextstep.ecomerce.service;

import com.nextstep.ecomerce.model.Admin;
import com.nextstep.ecomerce.model.Users;
import com.nextstep.ecomerce.repository.AdminRepository;
import com.nextstep.ecomerce.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) {
        Admin admin = adminRepository.findByUsername(identifier);
        Users users = usersRepository.findByEmail(identifier);
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getUsername(),
                    admin.getPassword(),
                    AuthorityUtils.createAuthorityList(admin.getRole())
            );
        }
        if (users != null) {
            return new org.springframework.security.core.userdetails.User(
                    users.getEmail(),
                    users.getPassword(),
                    AuthorityUtils.createAuthorityList(users.getRole())
            );
        }
        throw new UsernameNotFoundException("Not found"+ identifier);


    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<Users> users = usersRepository.findByEmail(email);
//
//        if (users == null) {
//            admin = adminRepository.findByUsername(email);
//        }
//
//        if (admin == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return new User(admin.getEmail(), admin.getPassword(), new ArrayList<>());
//    }


}

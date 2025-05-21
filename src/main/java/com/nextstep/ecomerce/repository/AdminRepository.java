package com.nextstep.ecomerce.repository;

import com.nextstep.ecomerce.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    Admin findByUsername(String identifier);
}

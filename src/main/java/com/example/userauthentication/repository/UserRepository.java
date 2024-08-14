package com.example.userauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userauthentication.entity.UserDtls;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {
    UserDtls findByEmail(String email);
}

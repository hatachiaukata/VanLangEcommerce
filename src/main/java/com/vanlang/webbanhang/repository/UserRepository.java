package com.vanlang.webbanhang.repository;

import com.vanlang.webbanhang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

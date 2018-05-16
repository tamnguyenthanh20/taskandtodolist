package com.tamnt.spring.sample.repository;

import com.tamnt.spring.sample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

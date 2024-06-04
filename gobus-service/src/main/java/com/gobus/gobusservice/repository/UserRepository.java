package com.gobus.gobusservice.repository;

import com.gobus.gobusservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmailId(String emailId);

    Optional<User> findByEmailId(String emailId);
}

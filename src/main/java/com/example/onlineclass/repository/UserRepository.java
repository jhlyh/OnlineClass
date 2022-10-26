package com.example.onlineclass.repository;

import com.example.onlineclass.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface UserRepository extends JpaRepository<User, Long> {
}

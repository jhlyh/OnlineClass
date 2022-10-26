package com.example.onlineclass.repository;

import com.example.onlineclass.domain.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface UserClassRepository extends JpaRepository<UserClass, Long> {
}

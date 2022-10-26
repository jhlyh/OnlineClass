package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface ClassRepository extends JpaRepository<Class, Long> {
}

package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
}

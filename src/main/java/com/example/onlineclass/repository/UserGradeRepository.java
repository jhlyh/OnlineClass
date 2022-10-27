package com.example.onlineclass.repository;

import com.example.onlineclass.domain.UserGrade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface UserGradeRepository extends JpaRepository<UserGrade, Long> {
}

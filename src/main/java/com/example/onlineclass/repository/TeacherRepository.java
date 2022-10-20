package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}

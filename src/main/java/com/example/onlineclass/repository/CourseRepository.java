package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author jhlyh
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}

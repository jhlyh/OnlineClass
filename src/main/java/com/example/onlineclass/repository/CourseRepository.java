package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.domain.CourseIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author jhlyh
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<CourseIdAndName> findAllBy();
}

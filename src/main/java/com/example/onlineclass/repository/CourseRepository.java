package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.domain.filter.CourseIdAndType;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;


/**
 * @author jhlyh
 */
public interface CourseRepository extends JpaRepository<Course, Long> , JpaSpecificationExecutor<Course> {
    List<CourseIdAndType> findAllBy();
    List<Course> findByNameLike(String name);
}

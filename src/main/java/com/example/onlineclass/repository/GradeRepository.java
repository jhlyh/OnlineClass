package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.domain.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;

/**
 * @author jhlyh
 */
public interface GradeRepository extends JpaRepository<Grade, Long> , JpaSpecificationExecutor<Grade> {
}

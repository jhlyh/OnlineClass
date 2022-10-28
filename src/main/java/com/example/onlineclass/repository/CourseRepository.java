package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.domain.filter.CourseIdAndType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * @author jhlyh
 */
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    /**
     * 返回所有的课程ID及其类型
     *
     * @return
     */
    List<CourseIdAndType> findAllBy();
}

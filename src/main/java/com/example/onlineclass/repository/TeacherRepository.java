package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.domain.TeacherIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author jhlyh
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<TeacherIdAndName> findAllBy();
}

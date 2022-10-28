package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.domain.filter.TeacherIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @author jhlyh
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    /**
     * 查询返回老师名字及其ID
     * @return
     */
    List<TeacherIdAndName> findAllBy();
}

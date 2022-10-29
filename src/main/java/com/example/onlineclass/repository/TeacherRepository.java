package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.domain.filter.TeacherIdAndName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


/**
 * @author jhlyh
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor<Teacher> {
    /**
     * 查询返回老师名字及其ID
     *
     * @return ID and Name
     */
    List<TeacherIdAndName> findAllBy();
}

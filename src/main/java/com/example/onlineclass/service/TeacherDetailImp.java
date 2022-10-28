package com.example.onlineclass.service;

import com.example.onlineclass.props.TeacherProps;
import com.example.onlineclass.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherDetailImp implements TeacherDetail {

    private final TeacherRepository teacherRepository;
    private final TeacherProps teacherProps;
    private final CommonDetailImp commonDetailImp;

    public TeacherDetailImp(TeacherRepository teacherRepository, TeacherProps teacherProps, CommonDetailImp commonDetailImp) {
        this.teacherRepository = teacherRepository;
        this.teacherProps = teacherProps;
        this.commonDetailImp = commonDetailImp;
    }

    /**
     * 调用内聚的commonDetailImp实现分页排序查询
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @Override
    public Map<String, Object> getAllTeachersPage(int page, int size, String[] sort) {
        return commonDetailImp.getAllPage(page, size, sort, teacherRepository, teacherProps);
    }
}

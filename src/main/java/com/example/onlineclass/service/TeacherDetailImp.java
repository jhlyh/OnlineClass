package com.example.onlineclass.service;

import com.example.onlineclass.config.TeacherProps;
import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherDetailImp implements TeacherDetail {

    private TeacherRepository teacherRepository;
    private TeacherProps teacherProps;
    private CommonDetailImp commonDetailImp;
    public TeacherDetailImp(TeacherRepository teacherRepository, TeacherProps teacherProps, CommonDetailImp commonDetailImp) {
        this.teacherRepository = teacherRepository;
        this.teacherProps = teacherProps;
        this.commonDetailImp = commonDetailImp;
    }

    @Override
    public Map<String, Object> getAllTeachersPage(int page, int size, String[] sort) {
        return commonDetailImp.getAllPage(page, size, sort, teacherRepository, teacherProps);
    }
}

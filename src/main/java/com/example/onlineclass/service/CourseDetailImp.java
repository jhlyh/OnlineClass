package com.example.onlineclass.service;

import com.example.onlineclass.config.CommonProps;
import com.example.onlineclass.config.CourseProps;
import com.example.onlineclass.domain.Course;
import com.example.onlineclass.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhlyh
 */
@Service
public class CourseDetailImp implements CourseDetail {
    private CourseRepository courseRepository;
    private CourseProps courseProps;
    private CommonProps commonProps;
    private CommonDetailImp commonDetailImp;

    public CourseDetailImp(CourseRepository courseRepository, CourseProps courseProps, CommonProps commonProps, CommonDetailImp commonDetailImp) {
        this.courseRepository = courseRepository;
        this.courseProps = courseProps;
        this.commonProps = commonProps;
        this.commonDetailImp =commonDetailImp;
    }

    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {
        return commonDetailImp.getAllPage(page,size,sort,courseRepository,courseProps);
    }
}

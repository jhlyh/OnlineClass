package com.example.onlineclass.service;

import com.example.onlineclass.props.CourseProps;
import com.example.onlineclass.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author jhlyh
 */
@Service
public class CourseDetailImp implements CourseDetail {
    private final CourseRepository courseRepository;
    private final CourseProps courseProps;
    private final CommonDetailImp commonDetailImp;

    @Autowired
    public CourseDetailImp(CourseRepository courseRepository, CourseProps courseProps, CommonDetailImp commonDetailImp) {
        this.courseRepository = courseRepository;
        this.courseProps = courseProps;
        this.commonDetailImp = commonDetailImp;
    }

    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {
        return commonDetailImp.getAllPage(page, size, sort, courseRepository, courseProps);
    }
}

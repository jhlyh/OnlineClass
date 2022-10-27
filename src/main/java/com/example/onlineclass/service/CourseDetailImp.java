package com.example.onlineclass.service;

import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.props.CourseProps;
import com.example.onlineclass.repository.CourseRepository;
import org.springframework.stereotype.Service;

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

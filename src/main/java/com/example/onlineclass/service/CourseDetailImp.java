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

    public CourseDetailImp(CourseRepository courseRepository, CourseProps courseProps, CommonProps commonProps) {
        this.courseRepository = courseRepository;
        this.courseProps = courseProps;
        this.commonProps = commonProps;
    }

    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {

        try {
            List<Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[courseProps.getSortDirectionIndex()]), _sort[courseProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[courseProps.getSortDirectionIndex()]), sort[courseProps.getTheSortByIndex()]));
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> coursePage;
            coursePage = courseRepository.findAll(pageable);
            List<Course> courses = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put(courseProps.getReturnCourses(), courses);
            response.put(courseProps.getReturnCurrentPage(), coursePage.getNumber());
            response.put(courseProps.getReturnTotalPages(), coursePage.getTotalPages());
            response.put(courseProps.getReturnTotalItems(), coursePage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

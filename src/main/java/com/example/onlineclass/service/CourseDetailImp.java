package com.example.onlineclass.service;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseDetailImp implements CourseDetail{
    private CourseRepository courseRepository;
    public CourseDetailImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {
        try{

            List<Order> orders = new ArrayList<>();
            if(sort[0].contains(",")) {
                for(String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[1]),_sort[0]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[1]),sort[0]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> coursePage;
            coursePage = courseRepository.findAll(pageable);
            List<Course> courses = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("courses", courses);
            response.put("currentPage", coursePage.getNumber());
            response.put("totalPages", coursePage.getTotalPages());
            response.put("totalItems", coursePage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

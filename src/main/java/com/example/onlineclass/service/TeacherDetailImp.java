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

    public TeacherDetailImp(TeacherRepository teacherRepository, TeacherProps teacherProps) {
        this.teacherRepository = teacherRepository;
        this.teacherProps = teacherProps;
    }

    @Override
    public Map<String, Object> getAllTeachersPage(int page, int size, String[] sort) {
        try {
            List<Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Sort.Direction.fromString(_sort[teacherProps.getSortDirectionIndex()]), _sort[teacherProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Order(Sort.Direction.fromString(sort[teacherProps.getSortDirectionIndex()]), sort[teacherProps.getTheSortByIndex()]));
            }
            List<Teacher> teachers;
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Teacher> teacherPage;
            teacherPage = teacherRepository.findAll(pagingSort);
            teachers = teacherPage.getContent();
            if (teachers.isEmpty()) {
                return null;
            }

            Map<String, Object> response = new HashMap<>();
            response.put(teacherProps.getReturnTeachers(), teachers);
            response.put(teacherProps.getReturnCurrentPage(), teacherPage.getNumber());
            response.put(teacherProps.getReturnTotalItems(), teacherPage.getTotalElements());
            response.put(teacherProps.getReturnTotalPages(), teacherPage.getTotalPages());

            return response;
        } catch (Exception e) {
            return null;
        }
    }
}

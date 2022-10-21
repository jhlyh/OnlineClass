package com.example.onlineclass.service;

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
public class TeacherDetailImp implements TeacherDetail{

    private TeacherRepository teacherRepository;
    public TeacherDetailImp(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Map<String, Object> getAllTeachersPage(int page, int size, String[] sort) {
        try{
            List<Order> orders = new ArrayList<>();
            if(sort[0].contains(",")) {
                for(String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Sort.Direction.fromString(_sort[1]),_sort[0]));
                }
            } else {
                orders.add(new Order(Sort.Direction.fromString(sort[1]),sort[0]));
            }
            List<Teacher> teachers = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page,size,Sort.by(orders));

            Page<Teacher> teacherPage;
            teacherPage = teacherRepository.findAll(pagingSort);
            teachers = teacherPage.getContent();
            if(teachers.isEmpty()) {
                return null;
            }

            Map<String, Object> response = new HashMap<>();
            response.put("teachers", teachers);
            response.put("currentPage", teacherPage.getNumber());
            response.put("totalItems", teacherPage.getTotalElements());
            response.put("totalPages", teacherPage.getTotalPages());

            return response;
        } catch (Exception e) {
            return null;
        }
    }
}

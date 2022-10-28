package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.Chapter;
import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.props.TeacherProps;
import com.example.onlineclass.repository.TeacherRepository;
import com.example.onlineclass.service.TeacherSevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherSeviceImp implements TeacherSevice {

    private final TeacherRepository teacherRepository;
    private final TeacherProps teacherProps;

    public TeacherSeviceImp(TeacherRepository teacherRepository, TeacherProps teacherProps) {
        this.teacherRepository = teacherRepository;
        this.teacherProps = teacherProps;
    }

    /**
     * 调用内聚的commonDetailImp实现分页排序查询
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @Override
    public Map<String, Object> getAllTeachersPage(String name, Integer page, Integer size, String[] sort) {
        try {
            Specification<Teacher> queryCondition = new Specification<Teacher>() {
                @Override
                public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (name != null) {
                        predicateList.add(criteriaBuilder.like(root.get("teacherName"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };

            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[teacherProps.getSortDirectionIndex()]), _sort[teacherProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[teacherProps.getSortDirectionIndex()]), sort[teacherProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Teacher> sectionPage = teacherRepository.findAll(queryCondition, pageable);
            List<Teacher> chapters = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(teacherProps.getReturnDomain(), chapters);
            response.put(teacherProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(teacherProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(teacherProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}

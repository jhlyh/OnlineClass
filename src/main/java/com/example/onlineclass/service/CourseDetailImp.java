package com.example.onlineclass.service;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.props.CourseProps;
import com.example.onlineclass.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, Object> getAllCoursesPage(Integer typeId,String name, Integer page, Integer size, String[] sort) {
        Specification<Course> queryCondition = new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(typeId != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("type"), typeId));
                }
                if(name != null) {
                    predicateList.add(criteriaBuilder.like(root.get("name"), name));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[courseProps.getSortDirectionIndex()]), _sort[courseProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[courseProps.getSortDirectionIndex()]), sort[courseProps.getTheSortByIndex()]));
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> aPage = courseRepository.findAll(queryCondition, pageable);
            List<Course> courses = aPage.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put(courseProps.getReturnDomain(), courses);
            response.put(courseProps.getReturnCurrentPage(), aPage.getNumber());
            response.put(courseProps.getReturnTotalPages(), aPage.getTotalPages());
            response.put(courseProps.getReturnTotalItems(), aPage.getTotalElements());
            return response;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}

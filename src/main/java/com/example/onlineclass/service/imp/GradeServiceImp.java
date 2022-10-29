package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.props.GradeProps;
import com.example.onlineclass.repository.GradeRepository;
import com.example.onlineclass.service.GradeService;
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
import java.util.*;

@Service
public class GradeServiceImp implements GradeService {
    private final GradeProps gradeProps;
    private final GradeRepository gradeRepository;


    public GradeServiceImp(GradeProps gradeProps, GradeRepository gradeRepository) {
        this.gradeProps = gradeProps;
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Map<String, Object> getAllGradePage(String name, Integer page, Integer size, String[] sort) {
        try {
            Specification<Grade> queryCondition = new Specification<Grade>() {
                @Override
                public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (name != null) {
                        predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };

            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[gradeProps.getSortDirectionIndex()]), _sort[gradeProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[gradeProps.getSortDirectionIndex()]), sort[gradeProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Grade> sectionPage = gradeRepository.findAll(queryCondition, pageable);
            List<Grade> grades = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(gradeProps.getReturnDomain(), grades);
            response.put(gradeProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(gradeProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(gradeProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}

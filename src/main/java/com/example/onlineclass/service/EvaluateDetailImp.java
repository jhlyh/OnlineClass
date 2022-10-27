package com.example.onlineclass.service;

import com.example.onlineclass.domain.Evaluate;
import com.example.onlineclass.props.EvaluateProps;
import com.example.onlineclass.repository.EvaluateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jhlyh
 */
@Service
public class EvaluateDetailImp implements EvaluateDetail {

    private final EvaluateRepository evaluateRepository;
    private final EvaluateProps evaluateProps;

    public EvaluateDetailImp(EvaluateRepository evaluateRepository, EvaluateProps evaluateProps) {
        this.evaluateRepository = evaluateRepository;
        this.evaluateProps = evaluateProps;
    }

    @Override
    public Map<String, Object> getAllEvaluatePage(Long courseId, int page, int size, String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[evaluateProps.getSortDirectionIndex()]), _sort[evaluateProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[evaluateProps.getSortDirectionIndex()]), sort[evaluateProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Evaluate> sectionPage = evaluateRepository.findByCourseId(courseId, pageable);
            List<Evaluate> chapters = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(evaluateProps.getReturnDomain(), chapters);
            response.put(evaluateProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(evaluateProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(evaluateProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

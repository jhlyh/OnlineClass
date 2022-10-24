package com.example.onlineclass.service;

import com.example.onlineclass.config.SectionProps;
import com.example.onlineclass.domain.Section;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.repository.SectionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SectionDetailImp implements SectionDetail {
    private SectionRepository sectionRepository;
    private SectionProps sectionProps;

    public SectionDetailImp(SectionRepository sectionRepository, SectionProps sectionProps) {
        this.sectionRepository = sectionRepository;
        this.sectionProps = sectionProps;
    }

    @Override
    public Map<String, Object> getAllSectionsPage(Long courseId, int page, int size, String[] sort) {
        try {
            List<Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[sectionProps.getSortDirectionIndex()]), _sort[sectionProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[sectionProps.getSortDirectionIndex()]), sort[sectionProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Section> sectionPage = sectionRepository.findByCourseId(courseId, pageable);
            List<Section> sections = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(sectionProps.getReturnSections(), sections);
            response.put(sectionProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(sectionProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(sectionProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}

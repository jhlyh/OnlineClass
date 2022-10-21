package com.example.onlineclass.service;

import com.example.onlineclass.domain.Section;
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
public class SectionDetailImp implements SectionDetail{
    private SectionRepository sectionRepository;
    public SectionDetailImp(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    @Override
    public Map<String, Object> getAllSectionsPage(int page, int size, String[] sort) {
        try{
            List<Order> orders = new ArrayList<>();
            if(sort[0].contains(",")) {
                for(String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[1]), _sort[1]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[1]),sort[0]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Section> sectionPage = sectionRepository.findAll(pageable);
            List<Section> sections = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put("sections",sections);
            response.put("totalPages", sectionPage.getTotalPages());
            response.put("currentPage", sectionPage.getNumber());
            response.put("totalItems",sectionPage.getTotalElements());

            return  response;

        } catch (Exception e) {
            return null;
        }
    }
}

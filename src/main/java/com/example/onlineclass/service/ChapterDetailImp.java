package com.example.onlineclass.service;

import com.example.onlineclass.config.ChapterProps;
import com.example.onlineclass.domain.Chapter;
import com.example.onlineclass.repository.ChapterRepository;
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

@Service
public class ChapterDetailImp implements ChapterDetail {
    private ChapterRepository chapterRepository;
    private ChapterProps chapterProps;

    public ChapterDetailImp(ChapterRepository chapterRepository, ChapterProps chapterProps) {
        this.chapterRepository = chapterRepository;
        this.chapterProps = chapterProps;
    }

    @Override
    public Map<String, Object> getAllChaptersPage(Long courseId, int page, int size, String[] sort) {
        try {
            List<Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[chapterProps.getSortDirectionIndex()]), _sort[chapterProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[chapterProps.getSortDirectionIndex()]), sort[chapterProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Chapter> sectionPage = chapterRepository.findByCourseId(courseId, pageable);
            List<Chapter> chapters = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(chapterProps.getReturnDomain(), chapters);
            response.put(chapterProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(chapterProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(chapterProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

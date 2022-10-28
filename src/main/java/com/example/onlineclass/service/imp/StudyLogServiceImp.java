package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.Chapter;
import com.example.onlineclass.domain.StudyLog;
import com.example.onlineclass.props.StudyLogProps;
import com.example.onlineclass.repository.StudyLogRepository;
import com.example.onlineclass.service.StudyLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudyLogServiceImp implements StudyLogService {

    private StudyLogProps studyLogProps;
    private StudyLogRepository studyLogRepository;

    public StudyLogServiceImp(StudyLogRepository studyLogRepository, StudyLogProps studyLogProps) {
        this.studyLogProps = studyLogProps;
        this.studyLogRepository = studyLogRepository;
    }

    /**
     * 根据用户Id分页排序查询学习记录实现
     * @param userId
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @Override
    public Map<String, Object> getAllStudyLogPage(Long userId, Integer page, Integer size, String[] sort) {
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[studyLogProps.getSortDirectionIndex()]), _sort[studyLogProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[studyLogProps.getSortDirectionIndex()]), sort[studyLogProps.getTheSortByIndex()]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<StudyLog> sectionPage = studyLogRepository.findByUserId(userId, pageable);
            List<StudyLog> chapters = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(studyLogProps.getReturnDomain(), chapters);
            response.put(studyLogProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(studyLogProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(studyLogProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

package com.example.onlineclass.service.imp;

import com.example.onlineclass.domain.Chapter;
import com.example.onlineclass.props.ChapterProps;
import com.example.onlineclass.repository.ChapterRepository;
import com.example.onlineclass.service.ChapterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author jhlyh
 */
@Service
public class ChapterServiceImp implements ChapterService {
    private final ChapterRepository chapterRepository;
    private final ChapterProps chapterProps;

    public ChapterServiceImp(ChapterRepository chapterRepository, ChapterProps chapterProps) {
        this.chapterRepository = chapterRepository;
        this.chapterProps = chapterProps;
    }

    /**
     * 根据课程Id和名字模糊查询
     *
     * @param courseId 课程ID
     * @param name     章节名
     * @param page     页码
     * @param size     页大小
     * @param sort     排序
     * @return map对象
     */
    @Override
    public Map<String, Object> getAllChaptersPage(Long courseId, String name, Integer page, Integer size, String[] sort) {
        try {
            Specification<Chapter> queryCondition = new Specification<Chapter>() {
                @Override
                public Predicate toPredicate(Root<Chapter> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicateList = new ArrayList<>();
                    if (courseId != null) {
                        predicateList.add(criteriaBuilder.equal(root.get("course"), courseId));
                    }
                    if (name != null) {
                        predicateList.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
                    }
                    return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
                }
            };

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
            Page<Chapter> sectionPage = chapterRepository.findAll(queryCondition, pageable);
            List<Chapter> chapters = sectionPage.getContent();
            Map<String, Object> response = new HashMap<>();

            response.put(chapterProps.getReturnDomain(), chapters);
            response.put(chapterProps.getReturnTotalPages(), sectionPage.getTotalPages());
            response.put(chapterProps.getReturnCurrentPage(), sectionPage.getNumber());
            response.put(chapterProps.getReturnTotalItems(), sectionPage.getTotalElements());

            return response;

        } catch (Exception e) {
            return Collections.emptyMap();
        }
    }
}

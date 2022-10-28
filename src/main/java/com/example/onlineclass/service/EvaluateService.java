package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface EvaluateService {
    /**
     * 根据课程ID提取课程评价进行分页排序查询
     * @param courseId
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllEvaluatePage(Long courseId, int page, int size, String[] sort);
}

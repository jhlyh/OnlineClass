package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface ChapterService {
    /**
     * 根据课程ID分页排序返回章节信息
     *
     * @param courseId
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllChaptersPage(Long courseId, Integer page, Integer size, String[] sort);
}

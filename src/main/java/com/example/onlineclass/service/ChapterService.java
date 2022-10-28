package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface ChapterService {
    /**
     *  根据课程Id和章节名字模糊分页排序查询
     * @param courseId
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllChaptersPage(Long courseId, String name, Integer page, Integer size, String[] sort);
}

package com.example.onlineclass.service;

import java.util.Map;

public interface CourseDetail {
    /**
     * 根据课程类型Id和课程名字模糊分页查询
     * @param typeId
     * @param name
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllCoursesPage(Integer typeId,String name,Integer page, Integer size, String[] sort);
}

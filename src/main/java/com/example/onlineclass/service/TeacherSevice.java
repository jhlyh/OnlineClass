package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface TeacherSevice {
    /**
     * 分页排序查询
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllTeachersPage(Integer page, Integer size, String[] sort);
}

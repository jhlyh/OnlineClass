package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface TeacherDetail {
    /**
     * 分页排序查询
     * @param page
     * @param size
     * @param sort
     * @return
     */
    Map<String, Object> getAllTeachersPage(int page, int size, String[] sort);
}

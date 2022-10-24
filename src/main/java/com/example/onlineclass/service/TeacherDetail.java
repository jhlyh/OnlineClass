package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface TeacherDetail {
    Map<String, Object> getAllTeachersPage(int page, int size, String[] sort);
}

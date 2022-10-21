package com.example.onlineclass.service;

import java.util.Map;

public interface CourseDetail {
    Map<String, Object> getAllCoursesPage(int page, int size, String[] sort);
}

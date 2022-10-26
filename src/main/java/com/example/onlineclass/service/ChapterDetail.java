package com.example.onlineclass.service;

import java.util.Map;

public interface ChapterDetail {
    Map<String, Object> getAllSectionsPage(Long courseId, int page, int size, String[] sort);
}

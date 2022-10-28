package com.example.onlineclass.service;

import java.util.Map;

public interface StudyLogService {
    Map<String, Object> getAllStudyLogPage(Long userId, Integer page, Integer size, String[] sort);
}

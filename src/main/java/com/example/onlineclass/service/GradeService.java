package com.example.onlineclass.service;

import java.util.Map;

public interface GradeService {
    Map<String, Object> getAllGradePage(String name, Integer page, Integer size, String[] sort);
}

package com.example.onlineclass.service;

import java.util.Map;

public interface GradeService {
    Map<String, Object> getAllGradePage(int page, int size, String[] sort);
}

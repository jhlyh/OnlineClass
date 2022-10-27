package com.example.onlineclass.service;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface EvaluateDetail {
    Map<String, Object> getAllEvaluatePage(Long courseId, int page, int size, String[] sort);
}

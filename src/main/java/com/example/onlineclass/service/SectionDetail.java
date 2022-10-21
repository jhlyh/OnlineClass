package com.example.onlineclass.service;

import java.util.Map;

public interface SectionDetail {
    Map<String, Object> getAllSectionsPage(int page, int size, String[] sort);
}

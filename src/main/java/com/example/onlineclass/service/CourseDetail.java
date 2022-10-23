package com.example.onlineclass.service;

import com.example.onlineclass.util.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CourseDetail {
    Map<String, Object> getAllCoursesPage(int page, int size, String[] sort);
    Result<?> uploadImage(MultipartFile multipartFile);
}

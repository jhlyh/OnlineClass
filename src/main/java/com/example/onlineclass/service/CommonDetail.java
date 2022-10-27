package com.example.onlineclass.service;

import com.example.onlineclass.config.Props;
import com.example.onlineclass.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface CommonDetail {
    Result<?> uploadFile(MultipartFile multipartFile, String path);
    Map<String, Object> getAllPage(int page, int size, String[] sort, JpaRepository jpaRepository, Props props);
}

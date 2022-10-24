package com.example.onlineclass.service;

import com.example.onlineclass.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jhlyh
 */
public interface CommonDetail {
    Result<?> uploadFile(MultipartFile multipartFile, String path);
}

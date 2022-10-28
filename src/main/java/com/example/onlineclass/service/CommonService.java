package com.example.onlineclass.service;

import com.example.onlineclass.props.flagInterface.Props;
import com.example.onlineclass.util.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author jhlyh
 */
public interface CommonService {
    /**
     * 上传文件接口
     * @param multipartFile
     * @param path
     * @return
     */
    Result<?> uploadFile(MultipartFile multipartFile, String path);

    /**
     * 分页排序接口
     * @param page
     * @param size
     * @param sort
     * @param jpaRepository
     * @param props
     * @return
     */
    Map<String, Object> getAllPage(int page, int size, String[] sort, JpaRepository jpaRepository, Props props);
}

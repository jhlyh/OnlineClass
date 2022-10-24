package com.example.onlineclass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
public class CourseProps {
    private String ftpPort = "21";
    private String ftpHost = "114.132.251.197";
    private String ftpUserName = "root";
    private String ftpPassword = "chCZD5hNS4wH7tL2";
    private String ftpPathName = "/images";
    private String nginxPath = "http://114.132.251.197:888/";
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnCourses = "courses";
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

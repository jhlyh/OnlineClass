package com.example.onlineclass.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author jhlyh
 */
@Data
@Component
public class TeacherProps {
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnTeachers = "teachers";
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

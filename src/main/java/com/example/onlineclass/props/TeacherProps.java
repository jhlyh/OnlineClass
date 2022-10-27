package com.example.onlineclass.props;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author jhlyh
 */
@Data
@Component
public class TeacherProps implements Props{
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnDomain = "teachers";
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

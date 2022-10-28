package com.example.onlineclass.props;

import com.example.onlineclass.props.flagInterface.Props;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CourseProps implements Props {
    private String returnDomain = "courses";
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

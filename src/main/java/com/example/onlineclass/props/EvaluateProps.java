package com.example.onlineclass.props;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EvaluateProps implements Props {
    private String returnDomain = "evaluates";
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

package com.example.onlineclass.props;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ChapterProps implements Props{
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnDomain = "sections";
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

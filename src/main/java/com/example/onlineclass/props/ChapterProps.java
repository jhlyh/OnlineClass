package com.example.onlineclass.props;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author jhlyh
 */
@Component
@Data
public class ChapterProps implements Props {
    private String returnDomain = "sections";
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

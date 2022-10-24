package com.example.onlineclass.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SectionProps {
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnSections = "sections";
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

package com.example.onlineclass.props;

import com.example.onlineclass.props.flagInterface.Props;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TypeProps implements Props {
    private String returnDomain = "types";
    private Integer sortDirectionIndex = 1;
    private Integer theSortByIndex = 0;
    private String returnCurrentPage = "currentPage";
    private String returnTotalPages = "totalPages";
    private String returnTotalItems = "totalItems";
}

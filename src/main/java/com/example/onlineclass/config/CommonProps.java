package com.example.onlineclass.config;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author jhlyh
 */
@Component
@Data
public class CommonProps {
    private String frontEndError = "4xx";
    private String AfterEndError = "5xx";
}

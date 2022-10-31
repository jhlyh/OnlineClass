package com.example.onlineclass.props;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author jhlyh
 */
@Component
@Data
public class CommonProps {
    private String ftpPort = "**";
    private String ftpHost = "**";
    private String ftpUserName = "**";
    private String ftpPassword = "**";
    private String ftpImagePathName = "/images";
    private String ftpVideoPathName = "/videos";
    private String nginxPath = "**";
    private String frontEndError = "4xx";
    private String afterEndError = "5xx";

}

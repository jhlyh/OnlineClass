package com.example.onlineclass.service;

import com.example.onlineclass.config.CommonProps;
import com.example.onlineclass.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xbib.io.ftp.client.FTP;
import org.xbib.io.ftp.client.FTPClient;
import org.xbib.io.ftp.client.FTPReply;

import java.util.UUID;

/**
 * @author jhlyh
 */
@Service
public class CommonDetailImp implements CommonDetail {

    private CommonProps commonProps;

    public CommonDetailImp(CommonProps commonProps) {
        this.commonProps = commonProps;
    }

    @Override
    public Result<?> uploadFile(MultipartFile multipartFile, String path) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;

        int port = Integer.parseInt(commonProps.getFtpPort());
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            ftpClient.connect(commonProps.getFtpHost(), port);
            ftpClient.login(commonProps.getFtpUserName(), commonProps.getFtpPassword());
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return Result.error(commonProps.getFrontEndError(), String.valueOf(result));
            }
            ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName, multipartFile.getInputStream());
        } catch (Exception e) {
            return Result.success(e.toString());
        }

        return Result.success(commonProps.getNginxPath() + fileName);
    }
}

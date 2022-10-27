package com.example.onlineclass.service;

import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.props.Props;
import com.example.onlineclass.domain.Course;
import com.example.onlineclass.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xbib.io.ftp.client.FTP;
import org.xbib.io.ftp.client.FTPClient;
import org.xbib.io.ftp.client.FTPReply;

import java.util.*;

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

    @Override
    public Map<String, Object> getAllPage(int page, int size, String[] sort, JpaRepository jpaRepository, Props props) {
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(",")) {
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(Sort.Direction.fromString(_sort[props.getSortDirectionIndex()]), _sort[props.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Sort.Order(Sort.Direction.fromString(sort[props.getSortDirectionIndex()]), sort[props.getTheSortByIndex()]));
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> coursePage;
            coursePage = jpaRepository.findAll(pageable);
            List<Course> courses = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put(props.getReturnDomain(), courses);
            response.put(props.getReturnCurrentPage(), coursePage.getNumber());
            response.put(props.getReturnTotalPages(), coursePage.getTotalPages());
            response.put(props.getReturnTotalItems(), coursePage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

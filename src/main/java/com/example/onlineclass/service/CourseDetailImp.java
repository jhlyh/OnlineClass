package com.example.onlineclass.service;

import com.example.onlineclass.config.CommonProps;
import com.example.onlineclass.config.CourseProps;
import com.example.onlineclass.domain.Course;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.util.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
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
public class CourseDetailImp implements CourseDetail{
    private CourseRepository courseRepository;
    private CourseProps courseProps;
    private CommonProps commonProps;
    public CourseDetailImp(CourseRepository courseRepository, CourseProps courseProps, CommonProps commonProps) {
        this.courseRepository = courseRepository;
        this.courseProps = courseProps;
        this.commonProps = commonProps;
    }

    @Override
    public Result<?> uploadImage(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;

        int port = Integer.parseInt(courseProps.getFtpPort());
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try{
            int reply;
            ftpClient.connect(courseProps.getFtpHost(), port);
            ftpClient.login(courseProps.getFtpUserName(), courseProps.getFtpPassword());
            reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return Result.error(commonProps.getFrontEndError(), String.valueOf(result));
            }
            ftpClient.changeWorkingDirectory(courseProps.getFtpPathName());
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName,multipartFile.getInputStream());
        } catch (Exception e) {
            return Result.success(e.toString());
        }

        return Result.success(courseProps.getNginxPath()+fileName);
    }
    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {

        try{
            List<Order> orders = new ArrayList<>();
            if(sort[0].contains(",")) {
                for(String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[courseProps.getSortDirectionIndex()]),_sort[courseProps.getTheSortByIndex()]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[courseProps.getSortDirectionIndex()]),sort[courseProps.getTheSortByIndex()]));
            }
            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> coursePage;
            coursePage = courseRepository.findAll(pageable);
            List<Course> courses = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put(courseProps.getReturnCourses(), courses);
            response.put(courseProps.getReturnCurrentPage(), coursePage.getNumber());
            response.put(courseProps.getReturnTotalPages(), coursePage.getTotalPages());
            response.put(courseProps.getReturnTotalItems(), coursePage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

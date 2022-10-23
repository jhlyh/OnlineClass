package com.example.onlineclass.service;

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

@Service
public class CourseDetailImp implements CourseDetail{
    private CourseRepository courseRepository;
    public CourseDetailImp(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Result<?> uploadImage(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;

        int port = Integer.parseInt("21");
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try{
            int reply;
            ftpClient.connect("114.132.251.197", 21);
            ftpClient.login("root", "chCZD5hNS4wH7tL2");
            reply = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return Result.error("400", String.valueOf(result));
            }

            ftpClient.changeWorkingDirectory("/images");
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(fileName,multipartFile.getInputStream());
        } catch (Exception e) {
            return Result.success(e.toString());
        }

        return Result.success("http://114.132.251.197:888/"+fileName);
    }
    @Override
    public Map<String, Object> getAllCoursesPage(int page, int size, String[] sort) {
        try{

            List<Order> orders = new ArrayList<>();
            if(sort[0].contains(",")) {
                for(String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(Direction.fromString(_sort[1]),_sort[0]));
                }
            } else {
                orders.add(new Order(Direction.fromString(sort[1]),sort[0]));
            }

            Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
            Page<Course> coursePage;
            coursePage = courseRepository.findAll(pageable);
            List<Course> courses = coursePage.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("courses", courses);
            response.put("currentPage", coursePage.getNumber());
            response.put("totalPages", coursePage.getTotalPages());
            response.put("totalItems", coursePage.getTotalElements());
            return response;

        } catch (Exception e) {
            return null;
        }
    }
}

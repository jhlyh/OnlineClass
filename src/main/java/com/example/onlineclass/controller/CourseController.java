package com.example.onlineclass.controller;

import com.example.onlineclass.util.Result;
import com.example.onlineclass.domain.Course;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.service.CourseDetailImp;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xbib.io.ftp.client.FTP;
import org.xbib.io.ftp.client.FTPClient;
import org.xbib.io.ftp.client.FTPReply;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseRepository courseRepository;
    private CourseDetailImp courseDetailImp;


    public CourseController(CourseRepository courseRepository, CourseDetailImp courseDetailImp) {
        this.courseRepository = courseRepository;
        this.courseDetailImp = courseDetailImp;
    }

    @PostMapping("/add")
    public Result<?> addCourse(@RequestBody Course course) {
        try{
            return Result.success(courseRepository.save(course));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody Course course) {
        try{
            return Result.success(courseRepository.save(course));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(courseDetailImp.getAllCoursesPage(page, size, sort));
    }
    @GetMapping("/find")
    public Result<?> findById(@RequestParam Long id) {
        try{
            Course course = courseRepository.findById(id).get();
            return Result.success(course);
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @DeleteMapping("delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            courseRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("500",e.toString());
        }
    }

    @PostMapping("/uploadImage")
    public Result<?> uploadImage(@RequestBody MultipartFile file) {
        return courseDetailImp.uploadImage(file);
    }
}

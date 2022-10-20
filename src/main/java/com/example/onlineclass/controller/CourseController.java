package com.example.onlineclass.controller;

import com.example.onlineclass.common.Result;
import com.example.onlineclass.domain.Course;
import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
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
    public Result<?> findAll() {
        return Result.success(courseRepository.findAll());
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
}

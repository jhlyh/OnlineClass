package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Course;
import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.service.CourseDetailImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseDetailImp courseDetailImp;
    private final CommonProps commonProps;


    public CourseController(CourseRepository courseRepository, CourseDetailImp courseDetailImp, CommonProps commonProps) {
        this.courseRepository = courseRepository;
        this.courseDetailImp = courseDetailImp;
        this.commonProps = commonProps;
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Course course) {
        try {
            return Result.success(courseRepository.save(course));
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Course course) {
        try {
            return Result.success(courseRepository.save(course));
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(required = false) Integer typeId,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        if(name == null) {
            return Result.success(courseDetailImp.getAllCoursesPage(typeId, name, page, size, sort));
        }else
        {
            return Result.success(courseDetailImp.getAllCoursesPage(typeId, "%" + name + "%", page, size, sort));
        }
    }

    @GetMapping("/find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Course course = courseRepository.findById(id).get();
            return Result.success(course);
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @GetMapping("/findByNameLike")
    public Result<?> findByNameLike(@RequestParam String name) {
        try {
            return Result.success(courseRepository.findByNameLike("%" + name + "%"));
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @DeleteMapping("delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            courseRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @GetMapping("/findIdType")
    public Result<?> findAllIdAndType() {
        try {
            return Result.success(courseRepository.findAllBy());
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }
}

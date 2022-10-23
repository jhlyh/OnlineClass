package com.example.onlineclass.controller;

import com.example.onlineclass.util.Result;
import com.example.onlineclass.domain.Section;
import com.example.onlineclass.repository.CourseRepository;
import com.example.onlineclass.repository.SectionRepository;
import com.example.onlineclass.service.SectionDetailImp;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/section")
public class SectionController {
    private SectionRepository sectionRepository;
    private SectionDetailImp sectionDetailImp;
    private CourseRepository courseRepository;
    public SectionController(SectionRepository sectionRepository, SectionDetailImp sectionDetailImp, CourseRepository courseRepository) {
        this.sectionRepository = sectionRepository;
        this.sectionDetailImp = sectionDetailImp;
        this.courseRepository = courseRepository;
    }
    @PostMapping("/add")
    public Result<?> addCourse(@RequestBody Section section) {
        try{
            return Result.success(sectionRepository.save(section));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody Section section) {
        try{
            return Result.success(sectionRepository.save(section));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @GetMapping("/findAll")
    public Result<?> findAllByCourseId(
            @RequestParam Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(sectionDetailImp.getAllSectionsPage(courseId, page, size, sort));
    }
    @GetMapping("/find")
    public Result<?> findById(@RequestParam Long id) {
        try{
            Section section = sectionRepository.findById(id).get();
            return Result.success(section);
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @DeleteMapping("delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            sectionRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("500",e.toString());
        }
    }
}

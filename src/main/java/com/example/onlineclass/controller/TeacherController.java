package com.example.onlineclass.controller;

import com.example.onlineclass.config.CommonProps;
import com.example.onlineclass.util.Result;
import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.repository.TeacherRepository;
import com.example.onlineclass.service.TeacherDetailImp;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherRepository teacherRepository;
    private TeacherDetailImp teacherDetailImp;
    private CommonProps commonProps;

    public TeacherController(TeacherRepository teacherRepository, TeacherDetailImp teacherDetailImp, CommonProps commonProps) {
        this.teacherRepository = teacherRepository;
        this.teacherDetailImp = teacherDetailImp;
        this.commonProps = commonProps;
    }

    @PostMapping("/add")
    public Result<?> addTeacher(@RequestBody Teacher teacher) {
        try {

            return Result.success(teacherRepository.save(teacher));
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @PostMapping("/update")
    public Result<?> updateTeacher(@RequestBody Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAllTeacher(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        return Result.success(teacherDetailImp.getAllTeachersPage(page, size, sort));
    }

    @GetMapping("find")
    public Result<?> findByIdTeacher(@RequestParam Long id) {
        try {
            Teacher teacher = teacherRepository.findById(id).get();
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            teacherRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }
    @GetMapping("/findIdName")
    public Result<?> findAllIdAndName() {
        try {
            return Result.success(teacherRepository.findAllBy());
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }
}

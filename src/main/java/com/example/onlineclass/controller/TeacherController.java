package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.repository.TeacherRepository;
import com.example.onlineclass.service.imp.TeacherSeviceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final TeacherSeviceImp teacherDetailImp;
    private final CommonProps commonProps;

    public TeacherController(TeacherRepository teacherRepository, TeacherSeviceImp teacherDetailImp, CommonProps commonProps) {
        this.teacherRepository = teacherRepository;
        this.teacherDetailImp = teacherDetailImp;
        this.commonProps = commonProps;
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Teacher teacher) {
        try {

            return Result.success(teacherRepository.save(teacher));
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {

        return Result.success(teacherDetailImp.getAllTeachersPage(page, size, sort));
    }

    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Teacher teacher = teacherRepository.findById(id).get();
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error(commonProps.getFrontEndError(), e.toString());
        }
    }

    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
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

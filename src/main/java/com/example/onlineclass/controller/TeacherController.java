package com.example.onlineclass.controller;

import com.example.onlineclass.common.Result;
import com.example.onlineclass.domain.Teacher;
import com.example.onlineclass.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private TeacherRepository teacherRepository;
    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @PostMapping("/add")
    public Result<?> addTeacher(@RequestBody Teacher teacher) {
        try {

            return Result.success(teacherRepository.save(teacher));
        } catch (Exception e) {
            return Result.error("400", e.toString());
        }
    }
    @PostMapping("/update")
    public Result<?> updateTeacher(@RequestBody Teacher teacher) {
        try {
            teacherRepository.save(teacher);
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error("400", e.toString());
        }
    }
    @GetMapping("/findAll")
    public Result<?> findAllTeacher() {
        return Result.success(teacherRepository.findAll());
    }

    @GetMapping("find")
    public Result<?> findByIdTeacher(@RequestParam Long id) {
        try{
            Teacher teacher = teacherRepository.findById(id).get();
            return Result.success(teacher);
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try{
            teacherRepository.deleteById(id);
            return Result.success();
        } catch (Exception e){
            return Result.error("500",e.toString());
        }
    }
}

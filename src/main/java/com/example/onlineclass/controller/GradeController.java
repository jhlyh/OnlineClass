package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.repository.GradeRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    private GradeRepository gradeRepository;
    public GradeController(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    @PostMapping("/add")
    public Result<?> addGrade(@RequestBody Grade grade) {
        try {
            return Result.success(gradeRepository.save(grade));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> updateGrade(@RequestBody Grade grade) {
        try {
            return Result.success(gradeRepository.save(grade));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 未完成
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("/findAll")
    public Result<?> findAllGrade(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(gradeRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findByIdGrade(@RequestParam Long id) {
        try {
            Grade grade = gradeRepository.findById(id).get();
            return Result.success(grade);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            gradeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.repository.GradeRepository;
import com.example.onlineclass.service.imp.GradeServiceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/grade")
public class GradeController {
    private final GradeRepository gradeRepository;
    private final GradeServiceImp gradeServiceImp;

    public GradeController(GradeRepository gradeRepository, GradeServiceImp gradeServiceImp) {
        this.gradeRepository = gradeRepository;
        this.gradeServiceImp = gradeServiceImp;
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Grade grade) {
        try {
            return Result.success(gradeRepository.save(grade));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    @PostMapping("update")
    public Result<?> update(@RequestBody Grade grade) {
        try {
            return Result.success(gradeRepository.save(grade));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(gradeServiceImp.getAllGradePage(page, size, sort));
    }

    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Grade grade = gradeRepository.findById(id).get();
            return Result.success(grade);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            gradeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

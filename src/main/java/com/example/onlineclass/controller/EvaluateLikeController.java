package com.example.onlineclass.controller;

import com.example.onlineclass.domain.EvaluateLike;
import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.repository.EvaluateLikeRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RequestMapping("/evaluateLike")
@RestController
public class EvaluateLikeController {
    private EvaluateLikeRepository evaluateLikeRepository;
    public EvaluateLikeController(EvaluateLikeRepository evaluateLikeRepository) {
        this.evaluateLikeRepository = evaluateLikeRepository;
    }
    @PostMapping("/add")
    public Result<?> addGrade(@RequestBody EvaluateLike evaluateLike) {
        try {
            return Result.success(evaluateLikeRepository.save(evaluateLike));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> updateGrade(@RequestBody EvaluateLike evaluateLike) {
        try {
            return Result.success(evaluateLikeRepository.save(evaluateLike));
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
        return Result.success(evaluateLikeRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findByIdGrade(@RequestParam Long id) {
        try {
            EvaluateLike evaluateLike = evaluateLikeRepository.findById(id).get();
            return Result.success(evaluateLike);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            evaluateLikeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

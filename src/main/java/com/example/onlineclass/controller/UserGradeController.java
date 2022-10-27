package com.example.onlineclass.controller;

import com.example.onlineclass.domain.UserGrade;
import com.example.onlineclass.repository.UserGradeRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/userGrade")
public class UserGradeController {
    private UserGradeRepository userGradeRepository;
    public UserGradeController(UserGradeRepository userGradeRepository) {
        this.userGradeRepository = userGradeRepository;
    }
    @PostMapping("/add")
    public Result<?> addGrade(@RequestBody UserGrade userGrade) {
        try {
            return Result.success(userGradeRepository.save(userGrade));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> updateGrade(@RequestBody UserGrade userGrade) {
        try {
            return Result.success(userGradeRepository.save(userGrade));
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
        return Result.success(userGradeRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findByIdGrade(@RequestParam Long id) {
        try {
            UserGrade userGrade = userGradeRepository.findById(id).get();
            return Result.success(userGrade);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            userGradeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

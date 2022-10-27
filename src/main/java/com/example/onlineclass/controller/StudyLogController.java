package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.domain.StudyLog;
import com.example.onlineclass.repository.StudyLogRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/studyLog")
public class StudyLogController {
    private StudyLogRepository studyLogRepository;
    public StudyLogController(StudyLogRepository studyLogRepository) {
        this.studyLogRepository = studyLogRepository;
    }
    @PostMapping("/add")
    public Result<?> addGrade(@RequestBody StudyLog studyLog) {
        try {
            return Result.success(studyLogRepository.save(studyLog));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> updateGrade(@RequestBody StudyLog studyLog) {
        try {
            return Result.success(studyLogRepository.save(studyLog));
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
        return Result.success(studyLogRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findByIdGrade(@RequestParam Long id) {
        try {
            StudyLog studyLog = studyLogRepository.findById(id).get();
            return Result.success(studyLog);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            studyLogRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

package com.example.onlineclass.controller;

import com.example.onlineclass.domain.StudyLog;
import com.example.onlineclass.repository.StudyLogRepository;
import com.example.onlineclass.service.imp.StudyLogServiceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/studyLog")
public class StudyLogController {
    private final StudyLogRepository studyLogRepository;
    private final StudyLogServiceImp studyLogServiceImp;

    public StudyLogController(StudyLogRepository studyLogRepository, StudyLogServiceImp studyLogServiceImp) {
        this.studyLogServiceImp = studyLogServiceImp;
        this.studyLogRepository = studyLogRepository;
    }

    /**
     * 增加
     *
     * @param studyLog
     * @return
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody StudyLog studyLog) {
        try {
            return Result.success(studyLogRepository.save(studyLog));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 更新
     *
     * @param studyLog
     * @return
     */
    @PostMapping("update")
    public Result<?> update(@RequestBody StudyLog studyLog) {
        try {
            return Result.success(studyLogRepository.save(studyLog));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 根据用户Id分页排序查询学习记录
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam Long userId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(studyLogServiceImp.getAllStudyLogPage(userId, page, size, sort));
    }

    /**
     * 根据id查询学习记录
     *
     * @param id
     * @return
     */
    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            StudyLog studyLog = studyLogRepository.findById(id).get();
            return Result.success(studyLog);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    /**
     * 根据Id删除学习记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            studyLogRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

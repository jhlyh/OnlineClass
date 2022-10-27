package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.domain.NoteLike;
import com.example.onlineclass.repository.NoteLikeRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("noteLike")
public class NoteLikeController {
    private NoteLikeRepository noteLikeRepository;
    public NoteLikeController(NoteLikeRepository noteLikeRepository) {
        this.noteLikeRepository = noteLikeRepository;
    }
    @PostMapping("/add")
    public Result<?> add(@RequestBody NoteLike noteLike) {
        try {
            return Result.success(noteLikeRepository.save(noteLike));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> update(@RequestBody NoteLike noteLike) {
        try {
            return Result.success(noteLikeRepository.save(noteLike));
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
    public Result<?> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(noteLikeRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            NoteLike noteLike = noteLikeRepository.findById(id).get();
            return Result.success(noteLike);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            noteLikeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

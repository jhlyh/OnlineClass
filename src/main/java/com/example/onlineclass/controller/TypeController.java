package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.domain.Type;
import com.example.onlineclass.repository.TypeRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    private TypeRepository typeRepository;
    public TypeController(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }
    @PostMapping("/add")
    public Result<?> add(@RequestBody Type type) {
        try {
            return Result.success(typeRepository.save(type));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> update(@RequestBody Type type) {
        try {
            return Result.success(typeRepository.save(type));
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
        return Result.success(typeRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Type type = typeRepository.findById(id).get();
            return Result.success(type);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            typeRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

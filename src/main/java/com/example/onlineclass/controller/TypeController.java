package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Type;
import com.example.onlineclass.repository.TypeRepository;
import com.example.onlineclass.service.imp.TypeServiceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    private final TypeRepository typeRepository;
    private final TypeServiceImp typeServiceImp;

    public TypeController(TypeRepository typeRepository, TypeServiceImp typeServiceImp) {
        this.typeServiceImp =typeServiceImp;
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
     * 根据类型模糊查询
     * @param name
     * @return
     */
    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam(required = false) String name
    ) {
        return Result.success(typeServiceImp.getAllType(name));
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

package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Evaluate;
import com.example.onlineclass.repository.EvaluateRepository;
import com.example.onlineclass.service.imp.EvaluateServiceImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {
    private final EvaluateRepository evaluateRepository;
    private final EvaluateServiceImp evaluateDetailImp;


    public EvaluateController(EvaluateRepository evaluateRepository, EvaluateServiceImp evaluateDetailImp) {
        this.evaluateRepository = evaluateRepository;
        this.evaluateDetailImp = evaluateDetailImp;
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Evaluate evaluate) {
        try {
            return Result.success(evaluateRepository.save(evaluate));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    @PostMapping("update")
    public Result<?> update(@RequestBody Evaluate evaluate) {
        try {
            return Result.success(evaluateRepository.save(evaluate));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAll(
            @RequestParam Long courseId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(evaluateDetailImp.getAllEvaluatePage(courseId, page, size, sort));
    }

    @GetMapping("find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Evaluate evaluate = evaluateRepository.findById(id).get();
            return Result.success(evaluate);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

    @DeleteMapping("/delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            evaluateRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }

}

package com.example.onlineclass.controller;

import com.example.onlineclass.common.Result;
import com.example.onlineclass.domain.Section;
import com.example.onlineclass.repository.SectionRepository;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/section")
public class SectionController {
    private SectionRepository sectionRepository;
    public SectionController(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    @PostMapping("/add")
    public Result<?> addCourse(@RequestBody Section section) {
        try{
            return Result.success(sectionRepository.save(section));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody Section section) {
        try{
            return Result.success(sectionRepository.save(section));
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @GetMapping("/findAll")
    public Result<?> findAll() {
        return Result.success(sectionRepository.findAll());
    }
    @GetMapping("/find")
    public Result<?> findById(@RequestParam Long id) {
        try{
            Section section = sectionRepository.findById(id).get();
            return Result.success(section);
        } catch (Exception e) {
            return Result.error("500", e.toString());
        }
    }
    @DeleteMapping("delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            sectionRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("500",e.toString());
        }
    }


}

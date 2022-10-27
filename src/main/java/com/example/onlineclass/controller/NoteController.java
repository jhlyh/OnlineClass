package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Grade;
import com.example.onlineclass.domain.Note;
import com.example.onlineclass.repository.NoteRepository;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/note")
public class NoteController {
    private NoteRepository noteRepository;
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository =noteRepository;
    }
    @PostMapping("/add")
    public Result<?> addGrade(@RequestBody Note note) {
        try {
            return Result.success(noteRepository.save(note));
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @PostMapping("update")
    public Result<?> updateGrade(@RequestBody Note note) {
        try {
            return Result.success(noteRepository.save(note));
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
        return Result.success(noteRepository.findAll());
    }
    @GetMapping("find")
    public Result<?> findByIdGrade(@RequestParam Long id) {
        try {
            Note note = noteRepository.findById(id).get();
            return Result.success(note);
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
    @DeleteMapping("/delete")
    public Result<?> deleteByIdTeacher(@RequestParam Long id) {
        try {
            noteRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error("出错啦", e.toString());
        }
    }
}

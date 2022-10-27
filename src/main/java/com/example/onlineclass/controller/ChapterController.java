package com.example.onlineclass.controller;

import com.example.onlineclass.domain.Chapter;
import com.example.onlineclass.props.CommonProps;
import com.example.onlineclass.repository.ChapterRepository;
import com.example.onlineclass.service.ChapterDetailImp;
import com.example.onlineclass.util.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @author jhlyh
 */
@RestController
@RequestMapping("/section")
public class ChapterController {
    private final ChapterRepository chapterRepository;
    private final ChapterDetailImp chapterDetailImp;
    private final CommonProps commonProps;

    public ChapterController(ChapterRepository chapterRepository, ChapterDetailImp chapterDetailImp, CommonProps commonProps) {
        this.chapterRepository = chapterRepository;
        this.chapterDetailImp = chapterDetailImp;
        this.commonProps = commonProps;
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody Chapter chapter) {
        try {
            return Result.success(chapterRepository.save(chapter));
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Chapter chapter) {
        try {
            return Result.success(chapterRepository.save(chapter));
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @GetMapping("/findAll")
    public Result<?> findAllByCourseId(
            @RequestParam Long courseId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        return Result.success(chapterDetailImp.getAllChaptersPage(courseId, page, size, sort));
    }

    @GetMapping("/find")
    public Result<?> findById(@RequestParam Long id) {
        try {
            Chapter chapter = chapterRepository.findById(id).get();
            return Result.success(chapter);
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }

    @DeleteMapping("delete")
    public Result<?> deleteById(@RequestParam Long id) {
        try {
            chapterRepository.deleteById(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(commonProps.getAfterEndError(), e.toString());
        }
    }
}

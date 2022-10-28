package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Chapter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    /**
     * 通过课程ID分页查询
     *
     * @param courseId
     * @param pageable
     * @return
     */
    Page<Chapter> findByCourseId(Long courseId, Pageable pageable);
}

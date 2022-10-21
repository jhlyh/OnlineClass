package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Section;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface SectionRepository extends JpaRepository<Section, Long> {
    Page<Section> findByCourseId(Long courseId, Pageable pageable);
}

package com.example.onlineclass.repository;

import com.example.onlineclass.domain.StudyLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface StudyLogRepository extends JpaRepository<StudyLog, Long> {
    /**
     * 通过用户Id查询学习记录
     *
     * @param userId
     * @param pageable
     * @return
     */
    Page<StudyLog> findByUserId(Long userId, Pageable pageable);
}

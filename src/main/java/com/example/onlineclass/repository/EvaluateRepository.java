package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Evaluate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface EvaluateRepository extends JpaRepository<Evaluate, Long> {
    Page<Evaluate> findByCourseId(Long courseId, Pageable pageable);
}

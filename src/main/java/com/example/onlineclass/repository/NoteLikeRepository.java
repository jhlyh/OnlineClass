package com.example.onlineclass.repository;

import com.example.onlineclass.domain.NoteLike;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface NoteLikeRepository extends JpaRepository<NoteLike, Long> {
}

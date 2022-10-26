package com.example.onlineclass.repository;

import com.example.onlineclass.domain.StudyLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyLogRepository extends JpaRepository<StudyLog, Long> {
}

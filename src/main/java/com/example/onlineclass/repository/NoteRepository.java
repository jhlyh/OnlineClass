package com.example.onlineclass.repository;

import com.example.onlineclass.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jhlyh
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
}

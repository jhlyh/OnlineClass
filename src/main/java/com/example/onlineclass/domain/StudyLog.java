package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jhlyh
 * @id 学习记录id
 * @createTime 创建时间
 * @chapter 该学习记录的章节
 * @user 该学习记录的用户
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class StudyLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 与章节为多对一关系
     */
    @ManyToOne
    @JoinTable(
            name = "chapter_log",
            joinColumns = @JoinColumn(name = "study_log"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    @JsonIgnoreProperties({"course", "notes"})
    private Chapter chapter;

    /**
     * 与用户为多对一关系
     */
    @ManyToOne
    @JoinTable(
            name = "user_study_log",
            joinColumns = @JoinColumn(name = "study_log"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties({"evaluateLike","noteLike", "studyLogs", "notes", "evaluates", "manageGrades", "teacher"})
    private User user;
}

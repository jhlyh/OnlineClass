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
 * @id 学习记录id;
 * @创建时间
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class StudyLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "chapter_log",
            joinColumns = @JoinColumn(name = "study_log"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    @JsonIgnoreProperties("studyLogs")
    private Chapter chapter;

    @ManyToOne
    @JoinTable(
            name = "user_study_log",
            joinColumns = @JoinColumn(name = "study_log"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties("studyLogs")
    private User user;
}

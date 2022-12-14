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
import java.util.List;

/**
 * @author jhlyh
 * @id 笔记点赞id
 * @createTime 创建时间
 * @users 给笔记点赞的用户
 * @note 点赞的笔记
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
public class NoteLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 与用户为一对多关系
     */
    @OneToMany(mappedBy = "noteLike")
    @JsonIgnoreProperties({"evaluateLike", "noteLike", "studyLogs", "notes", "evaluates", "manageGrades", "teacher"})
    private List<User> users;

    /**
     * 与笔记为多对一关系
     */
    @ManyToOne
    @JoinTable(
            name = "note_note_like",
            joinColumns = @JoinColumn(name = "note_like"),
            inverseJoinColumns = @JoinColumn(name = "note")
    )
    @JsonIgnoreProperties({"noteLikes", "chapter", "user"})
    private Note note;
}

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
 * @id 笔记id
 * @content 内容
 * @createTime 创建时间
 * @noteLikes 笔记的点赞
 * @chapter 章节
 * @user 写笔记的用户
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 与笔记点赞为一对多关系
     *
     */
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"note", "users"})
    private List<NoteLike> noteLikes;

    /** 与章节为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "chapter_note",
            joinColumns = @JoinColumn(name = "note"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    @JsonIgnoreProperties({"course", "notes"})
    private Chapter chapter;

    /** 与用户为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "user_notes",
            joinColumns = @JoinColumn(name = "note"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties({"evaluates", "teacher", "User", "manageGrades","notes", "studyLogs", "noteLike", "evaluateLike"})
    private User user;

}

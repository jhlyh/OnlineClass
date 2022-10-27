package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @id 用户名
 * @username 用户名
 * @nickName 昵称
 * @password 密码
 * @gender 性别
 * @avatarUrl 用户头像
 * @phone 电话
 * @email 邮箱
 * @openid 微信的openid
 * @teacher 如果有老师就对应一个用户
 * @grades 用户所属的班级
 * @manageGrades 如果用户是班主任，这是其对应的班级
 * @evaluates 用户发表的评价
 * @notes 用户发表的笔记
 * @studyLogs 用户的学习记录
 * @noteLike 用户对笔记的点赞
 * @evaluateLike 用户对评价的点赞
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String username;
    private String password;
    private String nickName;
    private Integer gender;
    private String avatarUrl;
    private String phone;
    private String email;
    private String openid;

    /** 与老师为一对一关系
     *
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Teacher teacher;

    /** 与班级为一对多关系
     *
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user", "aGrade"})
    private List<UserGrade> grades;

    /** 与管理班级为一对多关系（如果是班主任）
     *
     */
    @OneToMany(mappedBy = "headteacher")
    @JsonIgnore
    private List<Grade> manageGrades;

    /** 与评价为一对多关系
     *
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Evaluate> evaluates;

    /** 与笔记为一对多关系
     *
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"noteLikes", "chapter", "user"})
    private List<Note> notes;

    /** 与学习记录为一对多关系
     *
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"user", "chapter"})
    private List<StudyLog> studyLogs;

    /** 与笔记点赞为多对一关系
     *
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_note_like",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "note_like")
    )
    @JsonIgnore
    private NoteLike noteLike;

    /** 与评价点赞为多对一关系
     *
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_evaluate_like",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "evaluate_like")
    )
    @JsonIgnore
    private EvaluateLike evaluateLike;
}

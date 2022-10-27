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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserGrade> grades;

/*    @OneToMany
    @JsonIgnore
    private List<Grade> manageGrades;*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Evaluate> evaluates;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Note> notes;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<StudyLog> studyLogs;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_note_like",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "note_like")
    )
    @JsonIgnoreProperties("users")
    private NoteLike noteLike;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_evaluate_like",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "evaluate_like")
    )
    @JsonIgnoreProperties("users")
    private EvaluateLike evaluateLike;
}

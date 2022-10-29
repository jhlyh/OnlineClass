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
 * @teacherName 名字
 * @phone 手机号
 * @email 邮箱
 * @introduce 教师简介
 * @createTime 创建时间
 * @updateTime 更新时间
 * @courses 所授课程
 * @user 老师所属的用户
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@RequiredArgsConstructor
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String teacherName;
    private String phone;
    private String email;
    private String introduce;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 与用户为一对一关系
     */
    @OneToOne
    @JoinTable(
            name = "user_teacher",
            joinColumns = @JoinColumn(name = "teacher"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties({"evaluateLike", "noteLike", "studyLogs", "notes", "evaluates", "manageGrades", "teacher"})
    private User user;

    /**
     * 与课程为一对多关系
     */
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"teacher", "type", "evaluates", "chapters",})
    private List<Course> courses;

    /**
     * 老师管理班级，一对多
     */
    @OneToMany(mappedBy = "headteacher")
    @JsonIgnoreProperties({"headteacher", "classes"})
    private List<Grade> grades;
}

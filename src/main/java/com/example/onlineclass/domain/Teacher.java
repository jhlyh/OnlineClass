package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

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
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
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
    private Date createTime;
    private Date updateTime;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("teacher")
    private List<Course> courses;

    @OneToOne
    @JoinTable(
            name = "user_teacher",
            joinColumns = @JoinColumn(name = "teacher"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private User user;
}

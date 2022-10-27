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
 * @id 课程章节
 * @name; 课程名字
 * @Type; 课程类型（性质：公开课、定制课）
 * @introduction 课程简介
 * @coverUrl； 封面
 * @view 观看人数
 * @createTime 创建时间
 * @updateTime 更新时间
 * @period; 课时
 * @teacher 授课老师
 * @sections 课程章节
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    protected Integer view;
    private String name;
    private String coverUrl;
    private Integer period;
    private String introduction;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("course")
    private List<Evaluate> evaluates;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "course_type",
            joinColumns = @JoinColumn(name = "course"),
            inverseJoinColumns = @JoinColumn(name = "type")
    )
    @JsonIgnoreProperties("courses")
    private Type type;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("courses")
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "course"),
            inverseJoinColumns = @JoinColumn(name = "teacher")
    )
    private Teacher teacher;

}

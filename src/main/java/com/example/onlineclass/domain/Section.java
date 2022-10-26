package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jhlyh
 * @id 章节id
 * @name; 章节名字
 * @state; 章节学习进度
 * @course 所属课程
 * @info 章节简介
 * @videoUrl 视频地址
 * @parentSection 父章节（搁浅）
 * @sort 排序
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Section implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private String name;
    private Integer state;
    private String videoUrl;
    private String info;
    private Integer sort;

    @JsonIgnoreProperties("sections")
    @ManyToOne(optional = false)
    @JoinTable(
            name = "course_section",
            joinColumns = @JoinColumn(name = "section"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    private Course course;
}

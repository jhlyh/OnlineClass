package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private String name;
    private Integer state;
    private String videoUrl;
    private String info;
    private Integer sort;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chapter")
    private List<StudyLog> studyLogs;

    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("chapter")
    private List<Note> notes;

    @JsonIgnoreProperties("chapters")
    @ManyToOne(optional = false)
    @JoinTable(
            name = "course_chapter",
            joinColumns = @JoinColumn(name = "chapter"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    private Course course;
}

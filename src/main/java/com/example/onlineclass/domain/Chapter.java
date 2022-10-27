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
 * @author jhlyh
 * @id 章节id
 * @name; 章节名字
 * @state; 章节学习进度
 * @info 章节简介
 * @videoUrl 视频地址
 * @sort 排序
 * @studyLogs 学习记录
 * @notes 笔记
 * @course 所属课程
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

    /** 与学习记录为一对多关系，且章节具有级联权力
     *
     */
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudyLog> studyLogs;

    /**与学习笔记为一对多关系，且章节具有级联权力
     *
     */
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"noteLikes", "chapter", "user"})
    private List<Note> notes;

    /** 与课程为多对一关系,在JSON中忽略课程的本身，为关系维护方
     *
     */
    @ManyToOne
    @JsonIgnoreProperties({"teacher", "type", "evaluates", "chapters", })
    @JoinTable(
            name = "course_chapter",
            joinColumns = @JoinColumn(name = "chapter"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    private Course course;
}

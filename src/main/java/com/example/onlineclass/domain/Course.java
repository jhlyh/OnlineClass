package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author jhlyh
 * @name; 课程名字
 * @Type; 课程类型
 * @imageUrl； 封面
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

    private String name;
    private Integer Type;
    private String imageUrl;
    private Integer period;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("courses")
    @JoinTable(
            name = "teacher_course",
            joinColumns = @JoinColumn(name = "course"),
            inverseJoinColumns = @JoinColumn(name = "teacher")
    )
    private Teacher teacher;

    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Section> sections;
}

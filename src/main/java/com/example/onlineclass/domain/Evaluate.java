package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jhlyh
 * @id 课程评价id
 * @content 评价内容
 * @score 评价分数
 * @createTime 创建时间
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Evaluate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private String content;
    private Integer score;
    private Date createTime;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "course_evaluate",
            joinColumns = @JoinColumn(name = "evaluate"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    @JsonIgnoreProperties("evaluates")
    private Course course;

    @OneToMany(mappedBy = "evaluate", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("evaluate")
    private List<EvaluateLike> evaluateLikes;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "user_evaluate",
            joinColumns = @JoinColumn(name = "evaluate"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties("evaluates")
    private User user;
}

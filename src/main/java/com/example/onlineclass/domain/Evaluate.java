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
 * @id 课程评价id
 * @content 评价内容
 * @score 评价分数
 * @createTime 创建时间
 * @evaluatelike 评价点赞
 * @course 所属课程
 * @user 发表评论的人
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Evaluate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String content;
    private Integer score;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 与评价点赞为一对多关系，拥有级联权力
     *
     */
    @OneToMany(mappedBy = "evaluate", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("evaluate")
    private List<EvaluateLike> evaluateLikes;

    /** 与课程为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "course_evaluate",
            joinColumns = @JoinColumn(name = "evaluate"),
            inverseJoinColumns = @JoinColumn(name = "course")
    )
    @JsonIgnoreProperties("evaluates")
    private Course course;

    /** 与用户为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "user_evaluate",
            joinColumns = @JoinColumn(name = "evaluate"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties("evaluates")
    private User user;
}
